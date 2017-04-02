package org.eclectek.wanderlust

import io.circe._
import org.http4s._
import org.http4s.circe._
import org.http4s.server._
import org.http4s.dsl._
import scalaz.ValidationNel
import scalaz.Success
import scalaz.Failure
import scalaz.Validation
import scalaz.NonEmptyList

object ActivitiesService {
  val service = HttpService {
    case GET -> Root / "search" :? LatQueryParamMatcher(lat) +& 
    																LonQueryParamMatcher(lon) +& 
    																QueryQueryParamMatcher(query) +& 
    																RadiusQueryParamMatcher(radius) +&
    																NumberOfResultsQueryParamMatcher(numberOfResults) +&
    																ActivityTypeQueryParamMatcher(activityType) =>
    	Ok("Searching")      
  }



  implicit val activityTypeQueryParamDecoder = new QueryParamDecoder[ActivityTypes.ActivityType] {
  	def decode(queryParamValue: QueryParameterValue): ValidationNel[ParseFailure, ActivityTypes.ActivityType] = {
  		val maybeActivity = QueryParamDecoder[Int].decode(queryParamValue)
  		maybeActivity match {
  			case Failure(f) => 
  				Validation.failure[ParseFailure, ActivityTypes.ActivityType](
  					ParseFailure("Query decoding ActivityType failed", "Could not parse activity_type as a Int")
  				).toValidationNel
  			case Success(a) =>
					Validation.fromTryCatchNonFatal(ActivityTypes.activityTypes(a)).leftMap(t =>
		        ParseFailure("Query decoding ActivityType failed", t.getMessage)
		      ).toValidationNel
  		}
  	}
  }

  object LatQueryParamMatcher extends QueryParamDecoderMatcher[Double]("lat")
  object LonQueryParamMatcher extends QueryParamDecoderMatcher[Double]("lon")
  object QueryQueryParamMatcher extends OptionalQueryParamDecoderMatcher[String]("query")
  object RadiusQueryParamMatcher extends OptionalQueryParamDecoderMatcher[Int]("radius")
  object NumberOfResultsQueryParamMatcher extends OptionalQueryParamDecoderMatcher[Int]("number_of_results")
	object ActivityTypeQueryParamMatcher extends OptionalQueryParamDecoderMatcher[ActivityTypes.ActivityType]("tag")
  
}