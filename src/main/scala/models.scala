package org.eclectek.wanderlust



case class Activity(
	location: Location,
	title: String,
	description: String,
	rating: Float,
	numberOfRatings: Int,
	tag: ActivityTypes.ActivityType,
	primaryPhotoUrl: String,
	additionalPhotoUrls: Option[List[String]],
	address: Option[String],
	id: Int
)

case class Location(latitude: Double, longitude: Double)

object ActivityTypes {
	sealed trait ActivityType
	case object Hiking extends ActivityType
	case object Skiing extends ActivityType
	case object Swimming extends ActivityType
	val activityTypes = Seq(Hiking, Skiing, Swimming)
}