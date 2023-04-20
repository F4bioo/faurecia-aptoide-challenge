-dontobfuscate
# Uncomment this to preserve the line number information for
# debugging stack traces.
-keepattributes SourceFile, LineNumberTable

#####-- viewbinding
-keep class * implements androidx.viewbinding.ViewBinding {
    public static *** bind(android.view.View);
    public static *** inflate(android.view.LayoutInflater);
}
