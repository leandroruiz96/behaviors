# Android Behaviors
A list of simple [CoordinatorLayout.Behavior](https://developer.android.com/reference/android/support/design/widget/CoordinatorLayout.Behavior) examples showing how to achieve nice interactions with the user in just a few lines of code.

## Zoom out FAB

Just a linear interpolator can do a great job animating a FloatingActionButton.

![Zoom Out FAB](https://i.imgur.com/G0HI1wr.gif)

## SnackBar & FAB

Here I try to replicate the default FAB interaction with the SnackBar, but I added my own twist. No pun intended.

![SnackFAB](https://i.imgur.com/n24vunm.gif)

## Custom Tooltip

Extending a TextView, I assigned a default behavior for the tooltip to dismiss it when scrolling.

![Tooltip](https://i.imgur.com/iuYzUSU.gif)

## Chained FABs

I created a little more complex custom view extending FloatingActionButton, which expects to have a CoordinatorLayout as parent and a reference to another ChainedFab. The result is somewhat mesmerizing.

![ChainFAB](https://i.imgur.com/3RYHM3r.gif)

## ViewPager Progress

One of the simplest yet common is this one. A ProgressBar that reacts on ViewPager scroll events.

![ViewPagerProgress](https://i.imgur.com/yaeaLfU.gif)

## Continuity Lines

I wanted to show that you can create whatever relationship you can imagine, so I made two custom views that react to the ViewPager's scroll events in order to make you feel continuity between fragments, and with just one view on top of it.

![Lines](https://i.imgur.com/0C2lb85.gif)

## Coming soon

- A medium post explaining and encouraging the use of behaviors
- Remove redundant code in project
- Comments on behaviors
- Fix grammar and typos inside project


## License

MIT License

Copyright (c) 2018 Leandro Ezequiel Ruiz

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
