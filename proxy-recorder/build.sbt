name := "gatling-puppet-agent-capture"

version := "0.1.1-SNAPSHOT"

scalaVersion := "2.11.5"

libraryDependencies += "io.gatling" % "gatling-recorder" % "2.1.4" exclude("org.scala-lang", "scala-compiler")

libraryDependencies += "io.gatling" % "gatling-app" % "2.1.4"

libraryDependencies += "io.gatling.highcharts" % "gatling-charts-highcharts" % "2.1.4"

mainClass in (Compile, run) := Some("io.gatling.recorder.GatlingRecorder")

fork := true

javaOptions in run ++= Seq("-Xms512M", "-Xmx512M", "-Xmn100M",
  "-Drecorder.proxy.https.mode=ProvidedKeyStore",
  "-Drecorder.proxy.https.keyStore.path=./target/tmp/ssl/gatling-proxy-keystore.jks",
  "-Drecorder.proxy.https.keyStore.password=puppet",
  "-Drecorder.proxy.https.keyStore.type=JKS")
