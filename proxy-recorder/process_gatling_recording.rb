#!/usr/bin/env ruby

unless RUBY_VERSION =~ /^2\.\d+\.\d+$/
  puts "ERROR!  This script requires ruby 2.x."
  exit 1
end


require 'json'

HELPER_TO_HEADER = {
    "acceptHeader" => "Accept",
    "acceptEncodingHeader" => "Accept-Encoding",
    "userAgentHeader" => "User-Agent"
}

TERMINOLOGY = {
  "catalog"                                                 => "catalog",
  "file_metadata[s]?/modules/puppet_enterprise/mcollective" => "filemeta mco plugins",
  "file_metadata[s]?/pluginfacts"                           => "filemeta pluginfacts",
  "file_metadata[s]?/plugins"                               => "filemeta plugins",
  "file_metadata[s]?/modules"                               => "filemeta",
  "file_content/modules"                                    => "file content",
  "static_file_content/modules"                             => "static file content",
  "node"                                                    => "node",
  "report"                                                  => "report",
}

def lookup_request_match(line, prefix)
  TERMINOLOGY.each do |url, rewrite|
    if line.match(prefix + "/" + url)
      return rewrite
    end
  end
end

def grep_dir(dir, pattern)
  Dir.glob("#{dir}/**/*").each do |file|
    next unless File.file?(file)
    File.open(file) do |f|
      f.each_line do |line|
        if line.match(pattern)
          return File.absolute_path(file)
        end
      end
    end
  end
  nil
end

def prompt(msg)
  $stdout.write msg
  result = $stdin.gets.strip
  if result.empty?
    nil
  else
    result
  end
end

def comment_line(line)
  "// #{line}"
end

def get_simulation_runner_root_dir()
  File.join(File.dirname(__FILE__), "..", "simulation-runner")
end

def validate_infile_path(infile)
  infile_dir = File.absolute_path(File.dirname(infile))
  sim_dir = File.absolute_path(
                    File.join(get_simulation_runner_root_dir(),
                              "src", "main", "scala", "com", "puppetlabs",
                              "gatling", "node_simulations"))

  unless infile_dir == sim_dir
    puts "ERROR: Simulation recording must be placed in this directory: '#{sim_dir}'"
    puts "(specified input file is in dir '#{infile_dir}')"
    exit 1
  end
end

def find_certname(text)
  matches = text.match(/\n\s*\.get\("\/puppet\/v3\/node\/([^\?]+)\?environment/)
  unless matches
    puts "Unable to find certname (from node request) in recording!"
    exit 1
  end

  certname = matches[1]

  puts "Found certname: '#{certname}'"
  puts

  certname
end

def find_report_request_info(text)
  matches = text.match(/\n\s*\.put\("\/puppet\/v3\/report[^"]+"\)\s*\n\s*\.headers\(([^\)]+)\)\s*\n\s*\.body\(RawFileBody\("([^"]+)"\)\)\)\s*\n/)
  unless matches
    puts "Unable to find report request in recording!"
    exit 1
  end

  puts "Found report request."

  result = {:request_headers_varname => matches[1],
            :request_txt_file => matches[2]}

  request_txt = File.absolute_path(
                        File.join(get_simulation_runner_root_dir(), "user-files", "bodies",
                                  result[:request_txt_file]))

  unless File.file?(request_txt)
    puts "Unable to find report request body file!  Expected to find it at #{request_txt}"
    exit 1
  end

  result[:request_txt_file_path] = request_txt

  puts "\tReport request headers var: #{result[:request_headers_varname]}"
  puts "\tReport request text file: #{result[:request_txt_file_path]}"
  puts
  result
end

def find_node_config(simulation_class)
  node_configs_dir = File.join(get_simulation_runner_root_dir(), "config", "nodes")
  existing_node_config = grep_dir(node_configs_dir, /"simulation_class"\s*:\s*"#{simulation_class}"/)
  existing_node_config
end

def generate_node_config(simulation_class, certname)
  node_configs_dir = File.join(get_simulation_runner_root_dir(), "config", "nodes")
  node_config_filename = simulation_class.sub(/.*\.([^\.]+)$/, '\1') + ".json"
  node_config_file = File.absolute_path(File.join(node_configs_dir, node_config_filename))
  if File.exists?(node_config_file)
    puts "ERROR: file already exists at path '#{node_config_file}'; exiting."
    exit 1
  end

  data = prompt("Node config file name? [#{node_config_filename}]> ")
  if data
    node_config_file = File.absolute_path(File.join(node_configs_dir, data))
  end

  certname_prefix = certname.sub(/^([^\.]+)\..*$/, '\1').sub(/\d+$/, '')

  data = prompt("Certname prefix? (this will be used to classify nodes and generate certnames)\n" +
      "[#{certname_prefix}]>")
  if data
    certname_prefix = data
  end

  classes = []
  data = prompt("Puppet classes? (IMPORTANT!  This will be used to classify nodes,\n" +
                    "and *must* match up to what your recorded catalog was compiled\n" +
                    "with.  For best results use a single 'role' class, but if you\n" +
                    "need to provide multiple classes, separate them with a comma.)\n" +
                    "[]>")
  if data
    classes = data.split(",")
  end

  node_config = {:simulation_class => simulation_class,
                 :certname_prefix => certname_prefix,
                 :classes => classes
  }
  File.open(node_config_file, 'w') do |f|
    f.write(JSON.pretty_generate(node_config))
  end
  puts "Wrote node config file to '#{node_config_file}'"
end

# Creates a scala map from a ruby hash
def generate_scala_map(new_hash)
  inner = new_hash.map {|k, v| "\"#{k}\" -> \"#{v}\""}.join(",\n\t\t")

  "Map(#{inner})"
end

######################
#### Steps

def step1_look_for_inferred_html_resources(text)
  puts "STEP 1: Look for inferred HTML resources"

  if text.match(/\.inferHtmlResources\(\)/) or
      text.match(/\.resource\(http/)
    puts
    puts "ERROR: Found references to 'inferHtmlResources' and/or 'resources'.\n" +
             "This most likely means that you had the 'Infer Html resources?' checkbox\n" +
             "in the gatling proxy recorder GUI checked.  This causes the simulation to\n" +
             "try to behave like a browser and request multiple 'resources' in parallel;\n" +
             "this behavior is not suitable for simulating puppet agents.  Please re-record\n" +
             "your scenario with the 'Infer Html resources?' checkbox *unchecked*."
    exit 1
  end

end

# Step 2
def step2_rename_package(text)
  puts "STEP 2: Renaming package to `com.puppetlabs.gatling.node_simulations`"
  text.gsub!(/^\s*package (.*)/, "package com.puppetlabs.gatling.node_simulations")
end

# Step 3
def step3_add_new_imports(text)
  puts "STEP 3: Adding additional import statements (for SimulationWithScenario and date/time classes)"
  out_text = text.lines.map do |line|
    if line.match(/package com.puppetlabs.gatling.node_simulations$/)
      [line,
        "import com.puppetlabs.gatling.runner.SimulationWithScenario\n",
        "import org.joda.time.LocalDateTime\n",
        "import org.joda.time.format.ISODateTimeFormat\n",
        "import java.util.UUID\n",
      ]
    else
      line
    end
  end

  out_text.flatten.join
end

# Step 4
def step4_remove_unneeded_import(text)
  puts "STEP 4: Removing unused JDBC import"
  text.gsub!(/^\s*(import io\.gatling\.jdbc\.Predef\._)/, '// \1')
end

# Step 5
def step5_update_extends(text)
  puts "STEP 5: Set class to extend `SimulationWithScenario`"
  text.gsub!(/^class (.*) extends Simulation/, 'class \1 extends SimulationWithScenario')
end

def step_extract_http_protocol_headers(text)
  lines = text.lines
  found_header_data = {}

  http_protocol_start = lines.find_index {|line| line.match(/^.*val httpProtocol/)}

  if http_protocol_start.nil?
    puts "Can't find headers after httpProtocol definition"
    exit 1
  end

  index = http_protocol_start + 2
  loop do
    matches = lines[index].match(/\.(?<header_name>[^(]+)\(\"(?<header_value>[^"]+)\"\)/)
    if matches.nil?
      break
    end

    header_name = HELPER_TO_HEADER[matches[:header_name]]
    header_value = matches[:header_value]

    found_header_data[header_name] = header_value
    index += 1
  end

  found_header_data
end

def step_add_extracted_headers(text, headers)
  # Match until 2 newlines, meaning the whole block of code
  multiline_http_protocol_regex = /(val httpProtocol.*?)(?=\n\n)/m
  header_map_string = generate_scala_map(headers)

  text = text.gsub(multiline_http_protocol_regex, "\\1\n\n\tval baseHeaders = #{header_map_string}")

  text = text.gsub(/(val headers_\d+ = )/, '\1baseHeaders ++ ')

  puts text[0, 1000]

  text
end

# Step 6
def step6_comment_out_http_protocol(text)
  puts "STEP 6: Comment out `httpProtocol` variable"
  found_begin_comment = false
  found_end_comment = false

  out_text = text.lines.map do |line|
    found_begin_comment = true if line.match(/^.*val httpProtocol/)
    found_end_comment = true if found_begin_comment && line.match(/^$/)
    if found_begin_comment && !found_end_comment
      retline = comment_line(line)
    else
      retline = line
    end
    # This end check won't work with long chains

    retline
  end
  out_text.flatten.join

  # puts text.gsub(multiline_http_protocol_regex, )
end

# Step 7
def step7_add_connection_close(text, report_headers_var)
  puts "STEP 7: Add 'Connection: close' after report request"
  text.gsub!(/(\s*val #{report_headers_var} = Map\(\s*\n(?:\s*"[^"]+" -> "[^"]+",\s*\n)*(?:\s*"[^"]+" -> "[^"]+"))\)/,
       "\\1,\n\t\t\"Connection\" -> \"close\")\n//\n")
end

# Step 8
def step8_comment_out_uri1(text)
  puts "STEP 8: Comment out `uri1` variable"
  text.gsub!(/^\s*(val uri1 = ".*")/, '// \1')
end

# Step 9
def step9_update_expiration(text)
  puts "STEP 9: Update facts expiration date"
  text.gsub!(/(expiration%22%3A%22)(\d{4})/, '\12125')
end

# Step 10
def step10_comment_out_setup(text)
  puts "STEP 10: Comment out SCN setUp call since driver will handle it"
  text.gsub!(/^\s*(setUp\(.*\))/, '// \1')
end

# Step 11
def step11_rename_request_bodies(text)
  puts "STEP 11: Add names for HTTP requests"
  current_replacement = ""
  out_lines = []
  # Here we replace request types according to step 12 in
  # https://github.com/puppetlabs/gatling-puppet-load-test/blob/master/simulation-runner/README-GENERATING-AGENT-SIMULATIONS.md
  text.lines.reverse.each do |line|
    #TODO toggle v3 URLs (Puppet 4) vs Puppet 3 URLs
    if line.match(%r(\.(get|post|put)\("\/puppet\/v3\/))
      if lookup = lookup_request_match(line, "/puppet/v3")
        current_replacement = lookup
      else
        fail "Unrecognized request type. Please add to TERMINOLOGY."
      end
      out_lines << line
    elsif line.match(/\.?exec\(http\("request_\d+"\)/) && !current_replacement.empty?
      out_lines << line.sub(/request_\d+/, current_replacement)
      current_replacement = ""
    elsif line.match(/\.?exec\(http\("request_\d+"\)/) && current_replacement.empty?
      fail "Unexpected http request. Line: '#{line}' needs some work"
    else
      out_lines << line
    end
  end

  out_lines.reverse.join
end

# Step 12
def step12_add_dynamic_timestamp(text, report_text, report_request_info)
  # Match until 2 newlines, meaning the whole block of code
  multiline_http_protocol_regex = /(\/\/\s*val httpProtocol.*?)(?=\n\n)/m

  puts "STEP 12: Use dynamic timestamp and transaction UUID"
  text.gsub!(multiline_http_protocol_regex,
             "\\1\n\n\tval reportBody = ElFileBody(\"#{report_request_info[:request_txt_file]}\")")

  report_session_vars = <<EOS

\t\t.exec((session:Session) => {
\t\t\tsession.set("reportTimestamp",
\t\t\t\tLocalDateTime.now.toString(ISODateTimeFormat.dateTime()))
\t\t})
\t\t.exec((session:Session) => {
\t\t\tsession.set("transactionUuid",
\t\t\t\tUUID.randomUUID().toString())
\t\t})
EOS

  text.gsub!(/\n(\s*\.exec\(http\("report"\)\s*\n)/,
             "#{report_session_vars}\\1")
  text.gsub!(/(\n\s*)\.body\(RawFileBody\("#{report_request_info[:request_txt_file]}"\)\)\)\n/,
      "\\1.body(reportBody))\n\n")

  report_text.sub!(/"time":"[^"]+"/,
                   '"time":"${reportTimestamp}"')
  report_text.gsub!(/"transaction_uuid":"[^"]+"/,
                    '"transaction_uuid":"${transactionUuid}"')
end

def step13_setup_node_feeder(text, report_text, certname)
  puts "STEP 13: Set up ${node} var for feeder"
  text.gsub!(certname, "${node}")
  report_text.gsub!(certname, "${node}")
end

def main(infile, outfile)

  # validate_infile_path(infile)

  # The main event.
  # the input file tends to not end in a newline, which gets messy later
  input = File.read(infile) + "\n"

  puts "Reading input from file '#{infile}'"
  puts

  output = input.dup

  report_request_info = find_report_request_info(output)

  report_request_output = File.read(report_request_info[:request_txt_file_path])
  certname = find_certname(output)
  simulation_classname = "com.puppetlabs.gatling.node_simulations.#{File.basename(infile, ".*")}"

  step1_look_for_inferred_html_resources(output)
  step2_rename_package(output)
  output = step3_add_new_imports(output)
  step4_remove_unneeded_import(output)
  step5_update_extends(output)

  headers = step_extract_http_protocol_headers(output)

  output = step_add_extracted_headers(output, headers)

  output = step6_comment_out_http_protocol(output)

  step7_add_connection_close(output, report_request_info[:request_headers_varname])
  step8_comment_out_uri1(output)
  step9_update_expiration(output)
  step10_comment_out_setup(output)
  output = step11_rename_request_bodies(output)
  step12_add_dynamic_timestamp(output, report_request_output, report_request_info)
  step13_setup_node_feeder(output, report_request_output, certname)

  puts "All steps completed, writing output to file '#{outfile}'"
  # Dump the reformatted file to disk
  File.open(outfile, 'w').write(output)
  File.open(report_request_info[:request_txt_file_path] + ".new", 'w').
      write(report_request_output)

  puts

  node_config = find_node_config(simulation_classname)
  node_config = true
  if node_config
    puts "Found existing node config at '#{node_config}'"
  else
    puts "No node config file found.  Let's generate one."
    puts
    generate_node_config(simulation_classname, certname)
  end
end

if $0 == __FILE__
  if ARGV[0] && File.exists?(ARGV[0])
    infile = ARGV[0]
    outfile = infile + ".new"
    main(infile, outfile)
  else
    fail "Input file is a required argument"
  end
end
