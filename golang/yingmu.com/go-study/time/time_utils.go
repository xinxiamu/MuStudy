// time_utils
package main

import (
	"fmt"
	"time"
)

func main() {
	p := fmt.Println

	now := time.Now()
	p(now)

	then := time.Date(
		2009, 11, 17, 20, 34, 58, 651387237, time.UTC)
	p(then)

	p(then.Year())
	p(then.Month())
	p(then.Day())
	p(then.Hour())
	p(then.Minute())
	p(then.Second())
	p(then.Nanosecond())
	p(then.Location())

	p(then.Weekday())

	//和当前日期比较
	p(then.Before(now))
	p(then.After(now))
	p(then.Equal(now))

	//日期相减
	diff := now.Sub(then)
	p(diff)

	p(diff.Hours())
	p(diff.Minutes())
	p(diff.Seconds())
	p(diff.Nanoseconds())

	//相加
	p(then.Add(diff))
	p(then.Add(-diff))

	//时间戳和日期转换
	secs := now.Unix()
	nanos := now.UnixNano()
	p(now)

	millis := nanos / 1000000
	p(secs)
	p(nanos)
	p(millis)

	p(time.Unix(secs, 0))
	p(time.Unix(0, nanos))
}
