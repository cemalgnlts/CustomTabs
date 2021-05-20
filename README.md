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

For anny question write [DroidScript Forum](https://groups.google.com/g/androidscript).
