import NativePackagerKeys._

name := "wanderlust_backend"

version := "0.1"

scalaVersion := "2.12.1"

enablePlugins(JavaAppPackaging)

val Http4sVersion = "0.15.7"

libraryDependencies ++= Seq(
 "org.http4s"     %% "http4s-blaze-server" % Http4sVersion,
 "org.http4s"     %% "http4s-circe"        % Http4sVersion,
 "org.http4s"     %% "http4s-dsl"          % Http4sVersion,
 "ch.qos.logback" %  "logback-classic"     % "1.2.1"
)