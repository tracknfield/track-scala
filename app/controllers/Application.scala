package controllers

import play.api._
import play.api.mvc._
import play.api.cache.Cache
import play.api.Play.current
import play.api.libs.json._

import play.api.db._

object Application extends Controller {
	def index = Action { implicit request =>
		var out = ""
		val conn = DB.getConnection()
		try {
			val queryMap = request.queryString.map { case (k,v) => k -> v.mkString }
			val data = Json.stringify(Json.toJson(queryMap))
			val ps = conn.prepareStatement("INSERT INTO track (data) VALUES (?)");
    		ps.setString(1, data);
			ps.executeUpdate()

		} finally {
			conn.close()
		}
		Ok(out)
	}
}
