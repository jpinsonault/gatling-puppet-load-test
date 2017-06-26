package com.puppetlabs.gatling.node_simulations

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
// import io.gatling.jdbc.Predef._
import com.puppetlabs.gatling.runner.SimulationWithScenario
import org.joda.time.LocalDateTime
import org.joda.time.format.ISODateTimeFormat
import java.util.UUID

class PE201523CatalogZero extends SimulationWithScenario {

//	val httpProtocol = http
//		.baseURL("https://perf-bl14.delivery.puppetlabs.net:8140")

	val headers_0 = Map("X-Puppet-Version" -> "4.2.3")

	val headers_3 = Map(
		"Accept" -> "pson, yaml, dot, binary",
		"X-Puppet-Version" -> "4.2.3")

	val headers_106 = Map(
		"Accept" -> "pson, yaml",
		"Content-Type" -> "text/pson",
		"X-Puppet-Version" -> "4.2.3",
    "Connection" -> "close")

val reportBody = ElFileBody("PE201523CatalogZero_0106_request.txt")

//    val uri1 = "https://perf-bl14.delivery.puppetlabs.net:8140/puppet/v3"

	val chain_0 = exec(http("node")
			.get("/puppet/v3/node/w3h244u9nwii0y5.delivery.puppetlabs.net?environment=production&configured_environment=production&transaction_uuid=17d243cc-12ce-4e49-adfc-bdd25d799c1d&fail_on_404=true")
			.headers(headers_0))
		.pause(113 milliseconds)
		.exec(http("filemeta pluginfacts")
			.get("/puppet/v3/file_metadatas/pluginfacts?environment=production&links=follow&recurse=true&source_permissions=use&ignore=.svn&ignore=CVS&ignore=.git&checksum_type=md5")
			.headers(headers_0))
		.pause(343 milliseconds)
		.exec(http("filemeta plugins")
			.get("/puppet/v3/file_metadatas/plugins?environment=production&links=follow&recurse=true&source_permissions=ignore&ignore=.svn&ignore=CVS&ignore=.git&checksum_type=md5")
			.headers(headers_0))
		.pause(484 milliseconds)
		.exec(http("catalog")
			.post("/puppet/v3/catalog/w3h244u9nwii0y5.delivery.puppetlabs.net")
			.headers(headers_3)
			.formParam("environment", "production")
			.formParam("facts_format", "pson")
			.formParam("facts", "%7B%22name%22%3A%22w3h244u9nwii0y5.delivery.puppetlabs.net%22%2C%22values%22%3A%7B%22aio_agent_build%22%3A%221.2.7%22%2C%22aio_agent_version%22%3A%221.2.7%22%2C%22architecture%22%3A%22x86_64%22%2C%22augeas%22%3A%7B%22version%22%3A%221.4.0%22%7D%2C%22augeasversion%22%3A%221.4.0%22%2C%22bios_release_date%22%3A%2207%2F30%2F2013%22%2C%22bios_vendor%22%3A%22Phoenix+Technologies+LTD%22%2C%22bios_version%22%3A%226.00%22%2C%22blockdevice_sda_model%22%3A%22Virtual+disk%22%2C%22blockdevice_sda_size%22%3A8589934592%2C%22blockdevice_sda_vendor%22%3A%22VMware%22%2C%22blockdevice_sdb_model%22%3A%22Virtual+disk%22%2C%22blockdevice_sdb_size%22%3A8589934592%2C%22blockdevice_sdb_vendor%22%3A%22VMware%22%2C%22blockdevice_sr0_model%22%3A%22VMware+IDE+CDR10%22%2C%22blockdevice_sr0_size%22%3A1073741312%2C%22blockdevice_sr0_vendor%22%3A%22NECVMWar%22%2C%22blockdevices%22%3A%22sda%2Csdb%2Csr0%22%2C%22boardmanufacturer%22%3A%22Intel+Corporation%22%2C%22boardproductname%22%3A%22440BX+Desktop+Reference+Platform%22%2C%22boardserialnumber%22%3A%22None%22%2C%22chassisassettag%22%3A%22No+Asset+Tag%22%2C%22chassistype%22%3A%22Other%22%2C%22custom_auth_conf%22%3A%22false%22%2C%22dhcp_servers%22%3A%7B%22eth0%22%3A%2210.32.22.9%22%2C%22system%22%3A%2210.32.22.9%22%7D%2C%22disks%22%3A%7B%22sda%22%3A%7B%22model%22%3A%22Virtual+disk%22%2C%22size%22%3A%228.00+GiB%22%2C%22size_bytes%22%3A8589934592%2C%22vendor%22%3A%22VMware%22%7D%2C%22sdb%22%3A%7B%22model%22%3A%22Virtual+disk%22%2C%22size%22%3A%228.00+GiB%22%2C%22size_bytes%22%3A8589934592%2C%22vendor%22%3A%22VMware%22%7D%2C%22sr0%22%3A%7B%22model%22%3A%22VMware+IDE+CDR10%22%2C%22size%22%3A%221.00+GiB%22%2C%22size_bytes%22%3A1073741312%2C%22vendor%22%3A%22NECVMWar%22%7D%7D%2C%22dmi%22%3A%7B%22bios%22%3A%7B%22release_date%22%3A%2207%2F30%2F2013%22%2C%22vendor%22%3A%22Phoenix+Technologies+LTD%22%2C%22version%22%3A%226.00%22%7D%2C%22board%22%3A%7B%22manufacturer%22%3A%22Intel+Corporation%22%2C%22product%22%3A%22440BX+Desktop+Reference+Platform%22%2C%22serial_number%22%3A%22None%22%7D%2C%22chassis%22%3A%7B%22asset_tag%22%3A%22No+Asset+Tag%22%2C%22type%22%3A%22Other%22%7D%2C%22manufacturer%22%3A%22VMware%2C+Inc.%22%2C%22product%22%3A%7B%22name%22%3A%22VMware+Virtual+Platform%22%2C%22serial_number%22%3A%22VMware-42+0f+ea+bd+32+f3+ce+89-f2+8c+71+8d+c0+d7+ea+6e%22%2C%22uuid%22%3A%22420FEABD-32F3-CE89-F28C-718DC0D7EA6E%22%7D%7D%2C%22domain%22%3A%22delivery.puppetlabs.net%22%2C%22facterversion%22%3A%223.1.1%22%2C%22filesystems%22%3A%22ext4%2Ciso9660%22%2C%22fqdn%22%3A%22w3h244u9nwii0y5.delivery.puppetlabs.net%22%2C%22gid%22%3A%22root%22%2C%22hardwareisa%22%3A%22x86_64%22%2C%22hardwaremodel%22%3A%22x86_64%22%2C%22hostname%22%3A%22w3h244u9nwii0y5%22%2C%22id%22%3A%22root%22%2C%22identity%22%3A%7B%22gid%22%3A0%2C%22group%22%3A%22root%22%2C%22uid%22%3A0%2C%22user%22%3A%22root%22%7D%2C%22interfaces%22%3A%22eth0%2Clo%22%2C%22ipaddress%22%3A%2210.32.120.112%22%2C%22ipaddress6%22%3A%22fe80%3A%3A250%3A56ff%3Afe8f%3A5492%22%2C%22ipaddress6_eth0%22%3A%22fe80%3A%3A250%3A56ff%3Afe8f%3A5492%22%2C%22ipaddress6_lo%22%3A%22%3A%3A1%22%2C%22ipaddress_eth0%22%3A%2210.32.120.112%22%2C%22ipaddress_lo%22%3A%22127.0.0.1%22%2C%22is_pe%22%3Afalse%2C%22is_virtual%22%3Atrue%2C%22kernel%22%3A%22Linux%22%2C%22kernelmajversion%22%3A%222.6%22%2C%22kernelrelease%22%3A%222.6.32-431.11.2.el6.x86_64%22%2C%22kernelversion%22%3A%222.6.32%22%2C%22load_averages%22%3A%7B%2215m%22%3A0.11%2C%221m%22%3A0.18%2C%225m%22%3A0.23%7D%2C%22macaddress%22%3A%2200%3A50%3A56%3A8f%3A54%3A92%22%2C%22macaddress_eth0%22%3A%2200%3A50%3A56%3A8f%3A54%3A92%22%2C%22manufacturer%22%3A%22VMware%2C+Inc.%22%2C%22memory%22%3A%7B%22swap%22%3A%7B%22available%22%3A%223.94+GiB%22%2C%22available_bytes%22%3A4227850240%2C%22capacity%22%3A%220%25%22%2C%22total%22%3A%223.94+GiB%22%2C%22total_bytes%22%3A4227850240%2C%22used%22%3A%220+bytes%22%2C%22used_bytes%22%3A0%7D%2C%22system%22%3A%7B%22available%22%3A%223.46+GiB%22%2C%22available_bytes%22%3A3712495616%2C%22capacity%22%3A%227.62%25%22%2C%22total%22%3A%223.74+GiB%22%2C%22total_bytes%22%3A4018876416%2C%22used%22%3A%22292.19+MiB%22%2C%22used_bytes%22%3A306380800%7D%7D%2C%22memoryfree%22%3A%223.46+GiB%22%2C%22memoryfree_mb%22%3A3540.51171875%2C%22memorysize%22%3A%223.74+GiB%22%2C%22memorysize_mb%22%3A3832.69921875%2C%22mountpoints%22%3A%7B%22%2F%22%3A%7B%22available%22%3A%2210.11+GiB%22%2C%22available_bytes%22%3A10860883968%2C%22capacity%22%3A%2211.17%25%22%2C%22device%22%3A%22%2Fdev%2Fmapper%2FVolGroup-lv_root%22%2C%22filesystem%22%3A%22ext4%22%2C%22options%22%3A%5B%22rw%22%5D%2C%22size%22%3A%2211.39+GiB%22%2C%22size_bytes%22%3A12226121728%2C%22used%22%3A%221.27+GiB%22%2C%22used_bytes%22%3A1365237760%7D%2C%22%2Fboot%22%3A%7B%22available%22%3A%22430.32+MiB%22%2C%22available_bytes%22%3A451219456%2C%22capacity%22%3A%2211.13%25%22%2C%22device%22%3A%22%2Fdev%2Fsda1%22%2C%22filesystem%22%3A%22ext4%22%2C%22options%22%3A%5B%22rw%22%5D%2C%22size%22%3A%22484.22+MiB%22%2C%22size_bytes%22%3A507744256%2C%22used%22%3A%2253.91+MiB%22%2C%22used_bytes%22%3A56524800%7D%7D%2C%22mtu_eth0%22%3A1500%2C%22mtu_lo%22%3A16436%2C%22netmask%22%3A%22255.255.240.0%22%2C%22netmask6%22%3A%22ffff%3Affff%3Affff%3Affff%3A%3A%22%2C%22netmask6_eth0%22%3A%22ffff%3Affff%3Affff%3Affff%3A%3A%22%2C%22netmask6_lo%22%3A%22ffff%3Affff%3Affff%3Affff%3Affff%3Affff%3Affff%3Affff%22%2C%22netmask_eth0%22%3A%22255.255.240.0%22%2C%22netmask_lo%22%3A%22255.0.0.0%22%2C%22network%22%3A%2210.32.112.0%22%2C%22network6%22%3A%22fe80%3A%3A%22%2C%22network6_eth0%22%3A%22fe80%3A%3A%22%2C%22network6_lo%22%3A%22%3A%3A1%22%2C%22network_eth0%22%3A%2210.32.112.0%22%2C%22network_lo%22%3A%22127.0.0.0%22%2C%22networking%22%3A%7B%22dhcp%22%3A%2210.32.22.9%22%2C%22domain%22%3A%22delivery.puppetlabs.net%22%2C%22fqdn%22%3A%22w3h244u9nwii0y5.delivery.puppetlabs.net%22%2C%22hostname%22%3A%22w3h244u9nwii0y5%22%2C%22interfaces%22%3A%7B%22eth0%22%3A%7B%22bindings%22%3A%5B%7B%22address%22%3A%2210.32.120.112%22%2C%22netmask%22%3A%22255.255.240.0%22%2C%22network%22%3A%2210.32.112.0%22%7D%5D%2C%22bindings6%22%3A%5B%7B%22address%22%3A%22fe80%3A%3A250%3A56ff%3Afe8f%3A5492%22%2C%22netmask%22%3A%22ffff%3Affff%3Affff%3Affff%3A%3A%22%2C%22network%22%3A%22fe80%3A%3A%22%7D%5D%2C%22dhcp%22%3A%2210.32.22.9%22%2C%22ip%22%3A%2210.32.120.112%22%2C%22ip6%22%3A%22fe80%3A%3A250%3A56ff%3Afe8f%3A5492%22%2C%22mac%22%3A%2200%3A50%3A56%3A8f%3A54%3A92%22%2C%22mtu%22%3A1500%2C%22netmask%22%3A%22255.255.240.0%22%2C%22netmask6%22%3A%22ffff%3Affff%3Affff%3Affff%3A%3A%22%2C%22network%22%3A%2210.32.112.0%22%2C%22network6%22%3A%22fe80%3A%3A%22%7D%2C%22lo%22%3A%7B%22bindings%22%3A%5B%7B%22address%22%3A%22127.0.0.1%22%2C%22netmask%22%3A%22255.0.0.0%22%2C%22network%22%3A%22127.0.0.0%22%7D%5D%2C%22bindings6%22%3A%5B%7B%22address%22%3A%22%3A%3A1%22%2C%22netmask%22%3A%22ffff%3Affff%3Affff%3Affff%3Affff%3Affff%3Affff%3Affff%22%2C%22network%22%3A%22%3A%3A1%22%7D%5D%2C%22ip%22%3A%22127.0.0.1%22%2C%22ip6%22%3A%22%3A%3A1%22%2C%22mtu%22%3A16436%2C%22netmask%22%3A%22255.0.0.0%22%2C%22netmask6%22%3A%22ffff%3Affff%3Affff%3Affff%3Affff%3Affff%3Affff%3Affff%22%2C%22network%22%3A%22127.0.0.0%22%2C%22network6%22%3A%22%3A%3A1%22%7D%7D%2C%22ip%22%3A%2210.32.120.112%22%2C%22ip6%22%3A%22fe80%3A%3A250%3A56ff%3Afe8f%3A5492%22%2C%22mac%22%3A%2200%3A50%3A56%3A8f%3A54%3A92%22%2C%22mtu%22%3A1500%2C%22netmask%22%3A%22255.255.240.0%22%2C%22netmask6%22%3A%22ffff%3Affff%3Affff%3Affff%3A%3A%22%2C%22network%22%3A%2210.32.112.0%22%2C%22network6%22%3A%22fe80%3A%3A%22%2C%22primary%22%3A%22eth0%22%7D%2C%22operatingsystem%22%3A%22CentOS%22%2C%22operatingsystemmajrelease%22%3A%226%22%2C%22operatingsystemrelease%22%3A%226.5%22%2C%22os%22%3A%7B%22architecture%22%3A%22x86_64%22%2C%22family%22%3A%22RedHat%22%2C%22hardware%22%3A%22x86_64%22%2C%22name%22%3A%22CentOS%22%2C%22release%22%3A%7B%22full%22%3A%226.5%22%2C%22major%22%3A%226%22%2C%22minor%22%3A%225%22%7D%2C%22selinux%22%3A%7B%22config_mode%22%3A%22enforcing%22%2C%22current_mode%22%3A%22enforcing%22%2C%22enabled%22%3Atrue%2C%22enforced%22%3Atrue%2C%22policy_version%22%3A%2224%22%7D%7D%2C%22osfamily%22%3A%22RedHat%22%2C%22partitions%22%3A%7B%22%2Fdev%2Fmapper%2FVolGroup-lv_root%22%3A%7B%22filesystem%22%3A%22ext4%22%2C%22mount%22%3A%22%2F%22%2C%22size%22%3A%2211.57+GiB%22%2C%22size_bytes%22%3A12419334144%2C%22uuid%22%3A%22a1b40949-3e6d-43bf-8d4e-59c5aaa680ab%22%7D%2C%22%2Fdev%2Fmapper%2FVolGroup-lv_swap%22%3A%7B%22filesystem%22%3A%22swap%22%2C%22size%22%3A%223.94+GiB%22%2C%22size_bytes%22%3A4227858432%2C%22uuid%22%3A%2211a8e4fd-7617-4c04-bc2b-e091c60329fb%22%7D%2C%22%2Fdev%2Fsda1%22%3A%7B%22filesystem%22%3A%22ext4%22%2C%22mount%22%3A%22%2Fboot%22%2C%22size%22%3A%22500.00+MiB%22%2C%22size_bytes%22%3A524288000%2C%22uuid%22%3A%22c9b8c4a4-7c49-4cb0-b7ee-6dd795e27c87%22%7D%2C%22%2Fdev%2Fsda2%22%3A%7B%22filesystem%22%3A%22LVM2_member%22%2C%22size%22%3A%227.51+GiB%22%2C%22size_bytes%22%3A8064598016%2C%22uuid%22%3A%22IjGuKV-6bj6-3Dxv-vnt1-RJgk-oCJh-6IueXx%22%7D%2C%22%2Fdev%2Fsdb1%22%3A%7B%22filesystem%22%3A%22LVM2_member%22%2C%22size%22%3A%228.00+GiB%22%2C%22size_bytes%22%3A8587160064%2C%22uuid%22%3A%22Nfhg70-64A3-LR3P-4kFG-veST-3gEo-2fSrD0%22%7D%7D%2C%22path%22%3A%22%2Fusr%2Flocal%2Fsbin%3A%2Fusr%2Flocal%2Fbin%3A%2Fsbin%3A%2Fbin%3A%2Fusr%2Fsbin%3A%2Fusr%2Fbin%3A%2Froot%2Fbin%22%2C%22pe_concat_basedir%22%3A%22%2Fopt%2Fpuppetlabs%2Fpuppet%2Fcache%2Fpe_concat%22%2C%22pe_razor_server_version%22%3A%22package+pe-razor-server+is+not+installed%22%2C%22physicalprocessorcount%22%3A2%2C%22platform_symlink_writable%22%3Atrue%2C%22platform_tag%22%3A%22el-6-x86_64%22%2C%22processor0%22%3A%22Intel%28R%29+Xeon%28R%29+CPU+E5-2680+v2+%40+2.80GHz%22%2C%22processor1%22%3A%22Intel%28R%29+Xeon%28R%29+CPU+E5-2680+v2+%40+2.80GHz%22%2C%22processorcount%22%3A2%2C%22processors%22%3A%7B%22count%22%3A2%2C%22isa%22%3A%22x86_64%22%2C%22models%22%3A%5B%22Intel%28R%29+Xeon%28R%29+CPU+E5-2680+v2+%40+2.80GHz%22%2C%22Intel%28R%29+Xeon%28R%29+CPU+E5-2680+v2+%40+2.80GHz%22%5D%2C%22physicalcount%22%3A2%7D%2C%22productname%22%3A%22VMware+Virtual+Platform%22%2C%22puppet_files_dir_present%22%3Afalse%2C%22puppetversion%22%3A%224.2.3%22%2C%22ruby%22%3A%7B%22platform%22%3A%22x86_64-linux%22%2C%22sitedir%22%3A%22%2Fopt%2Fpuppetlabs%2Fpuppet%2Flib%2Fruby%2Fsite_ruby%2F2.1.0%22%2C%22version%22%3A%222.1.7%22%7D%2C%22rubyplatform%22%3A%22x86_64-linux%22%2C%22rubysitedir%22%3A%22%2Fopt%2Fpuppetlabs%2Fpuppet%2Flib%2Fruby%2Fsite_ruby%2F2.1.0%22%2C%22rubyversion%22%3A%222.1.7%22%2C%22selinux%22%3Atrue%2C%22selinux_config_mode%22%3A%22enforcing%22%2C%22selinux_current_mode%22%3A%22enforcing%22%2C%22selinux_enforced%22%3Atrue%2C%22selinux_policyversion%22%3A%2224%22%2C%22serialnumber%22%3A%22VMware-42+0f+ea+bd+32+f3+ce+89-f2+8c+71+8d+c0+d7+ea+6e%22%2C%22ssh%22%3A%7B%22dsa%22%3A%7B%22fingerprints%22%3A%7B%22sha1%22%3A%22SSHFP+2+1+8ebb6d9078da9da416723cac88c46bca6fe0ec54%22%2C%22sha256%22%3A%22SSHFP+2+2+fd2bf6b3b04a115a7c8368a7f7e7738cf41d9e4c3eaf13033a38a53fe119ac69%22%7D%2C%22key%22%3A%22AAAAB3NzaC1kc3MAAACBAJGJ%2BwHqAvYTv3VEzhPo3rwiaVxesMSstvufqZIYNLci54bEy7Dlq%2B%2BscLFU7EjqhlnGG0HWZ%2BaHQpDY8TvvsRIw8Q9nyxHAonk0EtHu4RCF6MH1lo0IeAh12A0vEpaVgpQjKisfWuH%2FIQEi1wCCaVL0lsLc5CLe1wE%2ByKqWuyVrAAAAFQD%2BF89mMng0ZXHd9NKbsZO5LQ5vcQAAAIAN6ysU1N1SASyGwtWuAnkRKxusYgCpQ0L7hx7shXHiQ69WoQxzgUUb5AdF%2B%2BkMQdplcqrD2MIf%2B6hk5o4kzkPTEJTpfl7l%2Fqp2L6znDR5yjz%2BG4b499eD3UGmlLIOgb1R7v%2FmS7Q3Y0XYsh1nBJyupOiTIMT1g6f7pQkA%2F9eimgwAAAIAiqkmEyA%2B8HRxg02zyPVG3z0RBFQnqnjLO8GaVFLq%2B3j5HTYgHuyktn2Js2Qke0j8bJ7gs%2F9Zaxq2dxC9BCZV29VAtmp7DO6lZgh3qwvA1uUKraO2J5zzWZHhJ2c9%2FapGtySBeOD1lOgGiXLkn5x4R09kLs56f5n%2Bax2kVa4qs3A%3D%3D%22%7D%2C%22rsa%22%3A%7B%22fingerprints%22%3A%7B%22sha1%22%3A%22SSHFP+1+1+c48ea7d5f6f38b3797e1e672f98ddf4d31796456%22%2C%22sha256%22%3A%22SSHFP+1+2+b953c17a0af98e6fb1f3d06f2cc1e386a54e4868b2ea688ff950f4b8c5ed354a%22%7D%2C%22key%22%3A%22AAAAB3NzaC1yc2EAAAABIwAAAQEAr2ywcLVQDql7LcLz8DZvJLWuEdb86cwe%2Fq8TVqd7E1V0fKvXhZBXUN1aGeuvY%2BewfnlYh79ioDvslGCSh452caet3JljzhtU6mFtTpPm5FPofZ9a5SbR4oC5abFe8WH19KGI%2FHSsXEGqtNwNAcX6kfJSgcCRcPv%2BJg0rO4Goncv8hLEv%2Bek%2BizVpuAPPBvgHN1QLlfSlN0rlYvn62WDs0ecYTGpmy03JisfYubefJ25roOFxakx8NAdajb1Ce5IV7GxEn5BSe9mjGPI4UvDFYtDEMeKhDl8eKxICfcei8X6uUfunr1N4kIjn3Q78aloCFhzn37OSI71rjmYISOq3JQ%3D%3D%22%7D%7D%2C%22sshdsakey%22%3A%22AAAAB3NzaC1kc3MAAACBAJGJ%2BwHqAvYTv3VEzhPo3rwiaVxesMSstvufqZIYNLci54bEy7Dlq%2B%2BscLFU7EjqhlnGG0HWZ%2BaHQpDY8TvvsRIw8Q9nyxHAonk0EtHu4RCF6MH1lo0IeAh12A0vEpaVgpQjKisfWuH%2FIQEi1wCCaVL0lsLc5CLe1wE%2ByKqWuyVrAAAAFQD%2BF89mMng0ZXHd9NKbsZO5LQ5vcQAAAIAN6ysU1N1SASyGwtWuAnkRKxusYgCpQ0L7hx7shXHiQ69WoQxzgUUb5AdF%2B%2BkMQdplcqrD2MIf%2B6hk5o4kzkPTEJTpfl7l%2Fqp2L6znDR5yjz%2BG4b499eD3UGmlLIOgb1R7v%2FmS7Q3Y0XYsh1nBJyupOiTIMT1g6f7pQkA%2F9eimgwAAAIAiqkmEyA%2B8HRxg02zyPVG3z0RBFQnqnjLO8GaVFLq%2B3j5HTYgHuyktn2Js2Qke0j8bJ7gs%2F9Zaxq2dxC9BCZV29VAtmp7DO6lZgh3qwvA1uUKraO2J5zzWZHhJ2c9%2FapGtySBeOD1lOgGiXLkn5x4R09kLs56f5n%2Bax2kVa4qs3A%3D%3D%22%2C%22sshfp_dsa%22%3A%22SSHFP+2+1+8ebb6d9078da9da416723cac88c46bca6fe0ec54%5CnSSHFP+2+2+fd2bf6b3b04a115a7c8368a7f7e7738cf41d9e4c3eaf13033a38a53fe119ac69%22%2C%22sshfp_rsa%22%3A%22SSHFP+1+1+c48ea7d5f6f38b3797e1e672f98ddf4d31796456%5CnSSHFP+1+2+b953c17a0af98e6fb1f3d06f2cc1e386a54e4868b2ea688ff950f4b8c5ed354a%22%2C%22sshrsakey%22%3A%22AAAAB3NzaC1yc2EAAAABIwAAAQEAr2ywcLVQDql7LcLz8DZvJLWuEdb86cwe%2Fq8TVqd7E1V0fKvXhZBXUN1aGeuvY%2BewfnlYh79ioDvslGCSh452caet3JljzhtU6mFtTpPm5FPofZ9a5SbR4oC5abFe8WH19KGI%2FHSsXEGqtNwNAcX6kfJSgcCRcPv%2BJg0rO4Goncv8hLEv%2Bek%2BizVpuAPPBvgHN1QLlfSlN0rlYvn62WDs0ecYTGpmy03JisfYubefJ25roOFxakx8NAdajb1Ce5IV7GxEn5BSe9mjGPI4UvDFYtDEMeKhDl8eKxICfcei8X6uUfunr1N4kIjn3Q78aloCFhzn37OSI71rjmYISOq3JQ%3D%3D%22%2C%22staging_http_get%22%3A%22curl%22%2C%22swapfree%22%3A%223.94+GiB%22%2C%22swapfree_mb%22%3A4031.9921875%2C%22swapsize%22%3A%223.94+GiB%22%2C%22swapsize_mb%22%3A4031.9921875%2C%22system_uptime%22%3A%7B%22days%22%3A0%2C%22hours%22%3A1%2C%22seconds%22%3A5044%2C%22uptime%22%3A%221%3A24+hours%22%7D%2C%22timezone%22%3A%22PST%22%2C%22uptime%22%3A%221%3A24+hours%22%2C%22uptime_days%22%3A0%2C%22uptime_hours%22%3A1%2C%22uptime_seconds%22%3A5044%2C%22uuid%22%3A%22420FEABD-32F3-CE89-F28C-718DC0D7EA6E%22%2C%22virtual%22%3A%22vmware%22%2C%22clientcert%22%3A%22w3h244u9nwii0y5.delivery.puppetlabs.net%22%2C%22clientversion%22%3A%224.2.3%22%2C%22clientnoop%22%3Afalse%7D%2C%22timestamp%22%3A%222015-11-16T11%3A26%3A43.640565918-08%3A00%22%2C%22expiration%22%3A%222025-11-16T11%3A56%3A43.640960126-08%3A00%22%7D")
			.formParam("transaction_uuid", "17d243cc-12ce-4e49-adfc-bdd25d799c1d")
			.formParam("configured_environment", "production")
			.formParam("fail_on_404", "true"))
		.pause(2)
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero1/catalog_zero1_impl24.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero1/catalog_zero1_impl71.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero1/catalog_zero1_impl83.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero2/catalog_zero2_impl51.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero3/catalog_zero3_impl23.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero3/catalog_zero3_impl32.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero3/catalog_zero3_impl74.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero4/catalog_zero4_impl13.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero5/catalog_zero5_impl22.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero5/catalog_zero5_impl42.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero5/catalog_zero5_impl43.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero5/catalog_zero5_impl52.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero6/catalog_zero6_impl32.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero6/catalog_zero6_impl54.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero7/catalog_zero7_impl32.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero7/catalog_zero7_impl34.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero7/catalog_zero7_impl82.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero7/catalog_zero7_impl85.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero7/catalog_zero7_impl87.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero8/catalog_zero8_impl11.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero8/catalog_zero8_impl13.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero8/catalog_zero8_impl33.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero8/catalog_zero8_impl81.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero9/catalog_zero9_impl63.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero9/catalog_zero9_impl72.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero9/catalog_zero9_impl84.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero10/catalog_zero10_impl13.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero10/catalog_zero10_impl22.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero10/catalog_zero10_impl41.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero10/catalog_zero10_impl71.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero10/catalog_zero10_impl83.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero10/catalog_zero10_impl85.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero11/catalog_zero11_impl11.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero11/catalog_zero11_impl21.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero11/catalog_zero11_impl24.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero11/catalog_zero11_impl62.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero11/catalog_zero11_impl84.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero12/catalog_zero12_impl33.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero12/catalog_zero12_impl82.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero12/catalog_zero12_impl86.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero12/catalog_zero12_impl87.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero13/catalog_zero13_impl51.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero13/catalog_zero13_impl54.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero13/catalog_zero13_impl74.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero13/catalog_zero13_impl83.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero13/catalog_zero13_impl84.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero13/catalog_zero13_impl86.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero14/catalog_zero14_impl72.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero15/catalog_zero15_impl62.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero15/catalog_zero15_impl72.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero15/catalog_zero15_impl73.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero16/catalog_zero16_impl33.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero16/catalog_zero16_impl83.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero16/catalog_zero16_impl85.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero17/catalog_zero17_impl13.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero17/catalog_zero17_impl87.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero18/catalog_zero18_impl14.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero18/catalog_zero18_impl23.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero18/catalog_zero18_impl24.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero18/catalog_zero18_impl32.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero18/catalog_zero18_impl84.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero19/catalog_zero19_impl12.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero19/catalog_zero19_impl31.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero19/catalog_zero19_impl53.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero19/catalog_zero19_impl54.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero19/catalog_zero19_impl83.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero19/catalog_zero19_impl86.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero20/catalog_zero20_impl24.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero20/catalog_zero20_impl42.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero20/catalog_zero20_impl71.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero20/catalog_zero20_impl73.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero21/catalog_zero21_impl32.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero21/catalog_zero21_impl41.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero21/catalog_zero21_impl82.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero21/catalog_zero21_impl86.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero21/catalog_zero21_impl87.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero22/catalog_zero22_impl34.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero22/catalog_zero22_impl52.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero23/catalog_zero23_impl21.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero23/catalog_zero23_impl62.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero24/catalog_zero24_impl52.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero24/catalog_zero24_impl83.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero25/catalog_zero25_impl13.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero25/catalog_zero25_impl22.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero25/catalog_zero25_impl24.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero25/catalog_zero25_impl51.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero26/catalog_zero26_impl54.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero26/catalog_zero26_impl64.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero26/catalog_zero26_impl86.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero27/catalog_zero27_impl33.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero27/catalog_zero27_impl41.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero27/catalog_zero27_impl63.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))

val chain_1 = exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero28/catalog_zero28_impl13.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero28/catalog_zero28_impl33.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero28/catalog_zero28_impl51.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero28/catalog_zero28_impl72.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero28/catalog_zero28_impl84.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero29/catalog_zero29_impl71.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero29/catalog_zero29_impl86.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero30/catalog_zero30_impl34.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta")
			.get("/puppet/v3/file_metadata/modules/catalog_zero30/catalog_zero30_impl85.txt?environment=production&links=manage&checksum_type=md5&source_permissions=ignore")
			.headers(headers_0))
		.exec(http("filemeta mco plugins")
			.get("/puppet/v3/file_metadatas/modules/puppet_enterprise/mcollective/plugins?environment=production&links=manage&recurse=true&source_permissions=ignore&checksum_type=md5")
			.headers(headers_0))
		.pause(2)
    .exec((session:Session) => {
      session.set("reportTimestamp",
        LocalDateTime.now.toString(ISODateTimeFormat.dateTime()))
    })
    .exec((session:Session) => {
      session.set("transactionUuid",
        UUID.randomUUID().toString())
    })
		.exec(http("report")
			.put("/puppet/v3/report/w3h244u9nwii0y5.delivery.puppetlabs.net?environment=production&")
			.headers(headers_106)
			.body(reportBody))
					
	val scn = scenario("PE201523CatalogZero").exec(
		chain_0, chain_1)

// 	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}
