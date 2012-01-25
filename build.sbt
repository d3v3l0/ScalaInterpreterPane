import AssemblyKeys._

name := "ScalaInterpreterPane"

version := "0.20"

organization := "de.sciss"

scalaVersion := "2.9.1"

resolvers += "Clojars Repository" at "http://clojars.org/repo"

libraryDependencies ++= Seq(
   "org.scala-lang" % "scala-compiler" % "2.9.1",
   "jsyntaxpane" % "jsyntaxpane" % "0.9.5-b29"
)

retrieveManaged := true

scalacOptions ++= Seq( "-deprecation", "-unchecked" )

fork in run := true

// ---- publishing ----

publishTo <<= version { (v: String) =>
   Some( "Scala Tools Nexus" at "http://nexus.scala-tools.org/content/repositories/".+(
      if( v.endsWith( "-SNAPSHOT")) "snapshots/" else "releases/"
   ))
}

pomExtra :=
<licenses>
  <license>
    <name>LGPL v2.1+</name>
    <url>http://www.gnu.org/licenses/lgpl-2.1.txt</url>
    <distribution>repo</distribution>
  </license>
</licenses>

credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")

// ---- standalone ----

seq( assemblySettings: _* )

test in assembly := {}

seq( appbundle.settings: _* )

appbundle.icon := Some( file( "application.icns" ))