# CustomTabs

[![DroidScript](https://img.shields.io/badge/%E2%9D%A4-DroidScript-brightgreen?style=for-the-badge&labelColor=32c974&color=41db84&logoColor=violet)](http://droidscript.org)
[![DroidStore](https://img.shields.io/badge/DroidStore-Download%20Plugin-brightgreen?style=for-the-badge&labelColor=2196F3&color=217cf4)](https://ds.justplayer.de/uploads/72)

**Install with source codes:**
* Download project folder and build APK package with Android Studio or AIDE.
* Open APK package to your on phone.
* (Re)start DroidScript.

**Install with ppk file:**
* Download **customtabs.ppk** file from [DroidStore](https://ds.justplayer.de/uploads/72).
* Click **customtabs.ppk** file and select DroidScript.

# Usage
Put yours project top:
```js
app.LoadPlugin("CustomTabs")
```

Create an instance of the plugin object:
```js
tabs = app.CreateCustomTabs()
```

Use ``OpenUrl`` method for show a page:
```js
tabs.OpenUrl("https://droidscript.org")
```

# Demo
```js
app.LoadPlugin("CustomTabs")

function OnStart()
{
  lay = app.CreateLayout("Liner", "FillXY,VCenter")
  
  btn = app.AddButton(lay, "Show a web page")
  btn.SetOnTouch(btn_OnTouch)
  
  app.AddLayout(lay)
  
  tabs = app.CreateCustomTabs()
}

function btn_OnTouch()
{
  tabs.OpenUrl("https://droidscript.org")
}
```

For anny issues write [DroidScript Forum](https://groups.google.com/g/androidscript).

# License
![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)

    MIT License

    Copyright (c) 2021 Cemal Gönültaş

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.
