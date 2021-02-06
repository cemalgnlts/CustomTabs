app.CreateCustomTabs = () => new CustomTabsWrapperClass();

//Plugin wrapper class.
function CustomTabsWrapperClass( options )
{
    this.plg = _CreatePlugin( "com.candlelight.customtabs.plugins.user.CustomTabs", options ); 
	this.GetVersion = () => parseFloat( this.plg.Send("GetVersion") );
	
	// Open custom tabs
	this.OpenUrl = url => this.plg.Send("open", url); 
	this.GetCustomTabsPackageList = () => this.plg.Send("packageNames").split(",");
}
