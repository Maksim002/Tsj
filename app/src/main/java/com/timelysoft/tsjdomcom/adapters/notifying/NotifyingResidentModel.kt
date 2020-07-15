package com.timelysoft.tsjdomcom.adapters.notifying

import java.io.Serializable

class NotifyingResidentModel (
  val address: String
): Serializable{
  override fun toString(): String {
    return address
  }
}
