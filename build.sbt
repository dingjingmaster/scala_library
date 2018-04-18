
lazy val root = (project in file("."))
  .settings(
    name := "scala library",
    version := "0",
    scalaVersion := "2.11.8" 
  )
libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.5"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % "test"
