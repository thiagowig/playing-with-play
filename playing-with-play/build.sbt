import play.Project._

name := "playing-with-play"

version := "1.0"

libraryDependencies ++= Seq(
  javaJdbc, 
  javaJpa, 
  "org.hibernate" % "hibernate-entitymanager" % "3.6.9.Final"
  )

playJavaSettings
