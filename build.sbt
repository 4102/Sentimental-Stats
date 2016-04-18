name := "Sentimental-Stats"

version := "0.3"

scalaVersion := "2.11.8"

resolvers += "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies += "org.mongodb.scala" %% "mongo-scala-driver" % "1.1.0"
libraryDependencies += "edu.stanford.nlp" % "stanford-corenlp" % "3.6.0"
libraryDependencies += "io.argonaut" %% "argonaut" % "6.1"
libraryDependencies += "com.typesafe.play" %% "play-json" % "2.3.4"

// Allows you to cancel execution early with Ctrl + C
cancelable in Global := true

// Opens project in a forked JVM, allowing sys.exit without throwing exceptions.
fork in run := true

