/**
  * Think of this as an enumeration or different sports
  */
sealed abstract class Sport(name: String)
case object Soccer extends Sport("Soccer")
case object Basketball extends Sport("Basketball")
case object Football extends Sport("Football")
case object Tennis extends Sport("Tennis")
