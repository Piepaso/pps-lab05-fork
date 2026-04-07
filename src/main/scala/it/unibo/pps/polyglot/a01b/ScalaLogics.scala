package it.unibo.pps.polyglot.a01b

import it.unibo.pps.polyglot.OptionToOptional
import it.unibo.pps.polyglot.a01b.Logics
import it.unibo.pps.util.Optionals.{Optional, Optional as ScalaOptional}
import Optional.*
import it.unibo.pps.util.Streams.Stream
import Stream.*

import scala.jdk.javaapi.OptionConverters
import scala.util.Random

trait ScalaLogics:
  def hit(x: Int, y: Int): java.util.Optional[Integer]
  def won: Boolean

object ScalaLogics:
  def apply(size: Int, mines: Int): ScalaLogics = LogicsImpl(size, mines)

  /** solution and descriptions at https://bitbucket.org/mviroli/oop2019-esami/src/master/a01b/sol2/ */
  private class LogicsImpl(private val size: Int, private val minesNum: Int, rand: Random = Random()) extends ScalaLogics:
    private val RANGE = 1
    private val randPos = () => (rand.nextInt(size), rand.nextInt(size))
    private val minePos = iterate(randPos())(_ => randPos()).distinct().take(minesNum).toList
    private var hitted = 0

    def hit(x: Int, y: Int): java.util.Optional[Integer] =
      if (isMine(x, y) > 0)
        OptionToOptional(Empty())
      else
        hitted += 1
        val range = () => iterate(-RANGE)(_ + RANGE).take(RANGE*2 + 1)
        OptionToOptional(Just(range().flatMap(dx => range().map(dy => isMine(x + dx, y + dy))).reduce(0, _ + _ )))
    
    def won = hitted >= size * size - minesNum

    def isMine(x: Int, y: Int): Int = if minePos.contains((x, y)) then 1 else 0
