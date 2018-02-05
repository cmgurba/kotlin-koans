package iii_conventions

import java.sql.Time
import java.util.*

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int): Comparable<MyDate>{
    override fun compareTo(other: MyDate): Int {
        // terrible way you did it :
//        if (this.year > other.year) {
//            return 1
//        } else if (this.year < other.year) {
//            return -1
//        }
//
//        if (this.month > other.month) {
//            return 1
//        } else if (this.month < other.month) {
//            return -1
//        }
//
//        if (this.dayOfMonth > other.dayOfMonth) {
//            return 1
//        } else if (this.dayOfMonth < other.dayOfMonth) {
//            return -1
//        }
//        return 0
        // their way:
        // this is implicit.  else clause has
        return when {
            year != other.year -> this.year - other.year
            month != other.month -> this.month - other.month
            else -> dayOfMonth - other.dayOfMonth
        }
    }
}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class RepeatedTimeInterval(val ti: TimeInterval, val n: Int)

operator fun MyDate.plus(timeInterval: TimeInterval) = addTimeIntervals(timeInterval, 1)
operator fun MyDate.plus(timeIntervals: RepeatedTimeInterval) = addTimeIntervals(timeIntervals.ti, timeIntervals.n)
operator fun TimeInterval.times(number: Int) = RepeatedTimeInterval(this, number)

class DateRange(override val start: MyDate, override val endInclusive: MyDate): ClosedRange<MyDate>, Iterable<MyDate> {
    override fun contains(value: MyDate): Boolean = value >= start && value <= endInclusive

    override fun iterator(): Iterator<MyDate> = DateIterator(this)
}

// note: adding val before arg makes it a public varialbe on the class.
class DateIterator(val dateRange: DateRange): Iterator<MyDate> {
    var current = dateRange.start
    override fun next(): MyDate {
        // Didn't know about addtimeintervals.  helper function they gave you.
         //  nextDate = current.addTimeIntervals(TimeInterval.DAY, 1)
        // does this (but abstracted for day, week, year.):
        val c = Calendar.getInstance()
        c.set(current.year, current.month, current.dayOfMonth)
        c.add(Calendar.DAY_OF_MONTH, 1)
        val now = current
        current = MyDate(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH))
        return now
    }

    override fun hasNext(): Boolean = current <= dateRange.endInclusive
}
