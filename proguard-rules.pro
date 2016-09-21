# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\Android\adt-bundle-windows-x86_64-20131030\adt-bundle-windows-x86_64-20131030\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
-dontwarn com.google.android.gms.internal.zzhu

# autovalue
-dontwarn javax.lang.**
-dontwarn javax.tools.**
-dontwarn javax.annotation.**
-dontwarn autovalue.shaded.com.**
-dontwarn com.google.auto.value.**
-dontwarn javax.servlet.**
-dontwarn sun.misc.**
-dontwarn autovalue.shaded.org.apache.commons.**
-dontwarn org.apache.log4j.**
-dontwarn org.apache.log.**
-dontwarn org.apache.velocity.**
-dontwarn org.apache.tools.**
-dontwarn org.jdom.**
-dontwarn org.java.lang.**

-dontwarn retrofit2.Platform$Java8

# Ignore warnings: https://github.com/square/okhttp/wiki/FAQs
-dontwarn com.squareup.okhttp.internal.huc.**
# Ignore warnings: https://github.com/square/okio/issues/60
-dontwarn okio.**
# Ignore warnings: https://github.com/square/retrofit/issues/435
-dontwarn com.google.appengine.api.urlfetch.**

-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
   long producerIndex;
   long consumerIndex;
}

-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}

-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}

# Keep Retrofit

-keep class com.google.gson.** { *; }
-keep class com.google.inject.** { *; }
-keep class org.apache.http.** { *; }
-keep class org.apache.james.mime4j.** { *; }
-keep class javax.inject.** { *; }
-keep class retrofit.** { *; }
-keepclasseswithmembers class * {
    @retrofit.** *;
}
-keepclassmembers class * {
    @retrofit.** *;
}

# Proguard configuration for Jackson 2.x (fasterxml package instead of codehaus package)

-keep class com.fasterxml.jackson.databind.ObjectMapper {
    public <methods>;
    protected <methods>;
}
-keep class com.fasterxml.jackson.databind.ObjectWriter {
    public ** writeValueAsString(**);
}

-keepattributes *Annotation*,EnclosingMethod,Signature
-keepnames class com.fasterxml.jackson.** { *; }

-dontwarn com.fasterxml.jackson.databind.**
-dontwarn com.fasterxml.jackson.databind.PropertyNamingStrategy$LowerCaseWithUnderscoresStrategy

-keep class org.codehaus.** { *; }
-keep class com.fasterxml.jackson.annotation.** { *; }
-keepclassmembers public final enum org.codehaus.jackson.annotate.JsonAutoDetect$Visibility {
 public static final org.codehaus.jackson.annotate.JsonAutoDetect$Visibility *; }

-keep public class com.yelp.clientlib.entities.SearchResponse {
    public *;}

-keep public class com.yelp.clientlib.entities.SearchResponse$Builder {
    public *;
    public com.yelp.clientlib.entities.SearchResponse$Builder build();
    public com.yelp.clientlib.entities.SearchResponse$Builder businesses(java.util.ArrayList<com.yelp.clientlib.entities.Business>);
    public com.yelp.clientlib.entities.SearchResponse$Builder region(com.yelp.clientlib.entities.Region);
    public com.yelp.clientlib.entities.SearchResponse$Builder total(java.lang.Integer);
    }

-keep public class com.yelp.clientlib.entities.Region {
    public *;}

-keep public class com.yelp.clientlib.entities.Region$Builder {
    public *;
    public com.yelp.clientlib.entities.Region$Builder build();}

-keep public class com.yelp.clientlib.entities.Location {
    public *;}

-keep public class com.yelp.clientlib.entities.Location$Builder {
    public *;
    public com.yelp.clientlib.entities.Location$Builder build();}

-keep public class com.yelp.clientlib.entities.Review {
    public *;}

-keep public class com.yelp.clientlib.entities.Review$Builder {
    public *;
    public com.yelp.clientlib.entities.Review$Builder build();}

-keep public class com.yelp.clientlib.entities.Business {
    public *;}

-keep public class com.yelp.clientlib.entities.Business$Builder {
    public *;
    public com.yelp.clientlib.entities.Business$Builder build();}

-keep public class com.yelp.clientlib.entities.Span {
    public *;}

-keep public class com.yelp.clientlib.entities.Span$Builder {
    public *;
    public com.yelp.clientlib.entities.Span$Builder build();}

-keep public class com.yelp.clientlib.entities.Coordinate {
    public *;}

-keep public class com.yelp.clientlib.entities.Coordinate$Builder {
    public *;
    public com.yelp.clientlib.entities.Coordinate$Builder build();}

-keep public class com.yelp.clientlib.entities.User {
    public *;}

-keep public class com.yelp.clientlib.entities.User$Builder {
    public *;
    public com.yelp.clientlib.entities.User$Builder build();}

-keepclassmembers public class com.fasterxml.jackson.databind.PropertyNamingStrategy$LowerCaseWithUnderscoresStrategy {
   public <init>(...);
}

# OkHttp
-keepattributes Signature
-keepattributes *Annotation*
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }
-dontwarn okhttp3.**

-printmapping build/outputs/mapping/release/mapping.txt

