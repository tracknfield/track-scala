package com.example

import akka.actor.{ActorSystem, Props}
import akka.io.IO
import spray.can.Http
import util.Properties

object Boot extends App {
	implicit val system = ActorSystem("on-spray-can")
	val service = system.actorOf(Props[MyServiceActor], "demo-service")
	val port = Properties.envOrElse("PORT", "5000").toInt

	IO(Http) ! Http.Bind(service, "0.0.0.0", port)
}