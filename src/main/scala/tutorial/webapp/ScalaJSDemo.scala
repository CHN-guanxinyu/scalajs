package tutorial.webapp

import org.querki.{jquery => jq}
import jq._

import scala.scalajs.js
import js.Dynamic._
import scala.scalajs.js.annotation.JSExportTopLevel

object ScalaJSDemo extends App {

  //jq的两种方式
  val body = $( "body" )

  implicit class str2$( val s : String ){  def $ = jq $ s }
  val title = "<h1>Hello Scala</h1>" $

  //append到body
  body append title

  //按钮点击事件
  @JSExportTopLevel("btnOnClick")
  def btnOnClick() = "body".$ append "You click the button!<hr/>"

  //全局var/function
  global foo = "Hello Scala"

  global translator = ( sentence : String ) =>
      $.get(
        s"https://api.asilu.com/fanyi/?q=$sentence" , "" ,
        { case (data : js.Object , _ , _ ) => global.console log data },
        "jsonp"
      )

  global bar = ( arg : String , callback : js.Function1[String , Unit] ) => {
    println("do something")
    callback(s"arg : ${arg}\n callback!")
    println("finish!")
  }

}
