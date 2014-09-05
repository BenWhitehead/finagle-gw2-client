organization := "io.github.benwhitehead.gw2"

name := "finagle-gw2-client"

version := "0.1-SNAPSHOT"

scalaVersion := "2.10.3"

scalacOptions ++= Seq("-unchecked", "-deprecation")

javacOptions ++= Seq("-source", "1.7", "-target", "1.7")

javacOptions in doc := Seq("-source", "1.7")

resolvers += "Twitter" at "http://maven.twttr.com/"

libraryDependencies ++= Seq(
  "com.twitter"                   % "finagle-http_2.10"           % "6.20.0",
  "com.fasterxml.jackson.module"  % "jackson-module-scala_2.10"   % "2.4.2",
  "com.netaporter"                %% "scala-uri"                  % "0.4.2",
  "org.scalatest"                 %% "scalatest"                  % "2.2.2"   % "test"
)

parallelExecution in Test := true

publishMavenStyle := true

pomExtra :=
  <url>https://github.com/BenWhitehead/finagle-gw2-client</url>
  <licenses>
    <license>
      <name>Apache 2</name>
      <url>http://www.apache.org/licenses/LICENSE-2.0.txt</url>
      <distribution>repo</distribution>
      <comments>A business-friendly OSS license</comments>
    </license>
  </licenses>
  <scm>
    <url>git@github.com:BenWhitehead/finagle-gw2-client.git</url>
    <connection>scm:git:git@github.com:BenWhitehead/finagle-gw2-client.git</connection>
  </scm>
  <developers>
    <developer>
      <id>BenWhitehead</id>
      <name>Ben Whitehead</name>
      <url>https://github.com/BenWhitehead</url>
    </developer>
  </developers>

