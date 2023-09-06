package com.lazymohan.ciaoadmin

data class Booking(
  val createdAt: String? = null,
  val from: String? = null,
  val id: String? = null,
  @field:JvmField val isPickupCompleted: Boolean = false,
  val name: String? = null,
  val phoneNumber: String? = null,
  val pickUpDate: String? = null,
  val pickUptime: String? = null,
  val takeRide: Int = 0,
  val to: String? = null,
  val vType: String? = null,
  val driverName: String? = null,
  val driverNum: String? = null,
) {
  fun toMap(): Map<String, Any?> {
    return mapOf(
      "from" to from,
      "to" to to,
      "name" to name,
      "isPickupCompleted" to isPickupCompleted,
      "phoneNumber" to phoneNumber,
      "pickUpDate" to pickUpDate,
      "pickUptime" to pickUptime,
      "vType" to vType,
      "id" to id,
      "takeRide" to takeRide,
      "createdAt" to createdAt,
      "driverName" to driverName,
      "driverNum" to driverNum
    )
  }
}