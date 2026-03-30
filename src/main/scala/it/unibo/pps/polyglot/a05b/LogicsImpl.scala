package it.unibo.pps.polyglot.a05b

import it.unibo.pps.ex.Vector2D
import it.unibo.pps.polyglot.a05b.Logics
import it.unibo.pps.util.Sequences.Sequence
import Sequence.*

/** solution and descriptions at https://bitbucket.org/mviroli/oop2019-esami/src/master/a05b/sol2/ */
class LogicsImpl(private val size: Int) extends Logics:

  private val random = scala.util.Random(42)
  private val centre: (Int, Int) = (random.nextInt(size - 2) + 1, random.nextInt(size - 2) + 1)
  private var epoch = 0

  override def tick(): Unit = epoch += 1

  override def isOver: Boolean = Math.min(centre._1, centre._2) - epoch < 0 || Math.max(centre._1, centre._2) - epoch >= size

  override def hasElement(x: Int, y: Int): Boolean = 
