require 'puppet/gatling/config'

test_name 'Configure java args'

def service_config_name(service_name)
  "/etc/sysconfig/#{service_name}"
end

def set_bash_variable(host, filepath, variable_name, value)
  manifest = <<-MANIFEST
ini_setting { "#{variable_name}":
  ensure  => present,
  path    => "#{filepath}",
  section => "",
  setting => "#{variable_name}",
  key_val_separator => "=",
  value   => "#{value}",
}
  MANIFEST

  on host, puppet('apply', '-e', "'#{manifest}'")
end

service_name = get_puppet_server_service_name_from_env()
service_config = service_config_name(service_name)
java_args = get_puppet_server_java_args_from_env()

set_bash_variable(master, service_config, 'JAVA_ARGS', java_args)
