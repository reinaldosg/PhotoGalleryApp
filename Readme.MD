## PHOTO GALLERY APP

This app is using UnsplashAPI (https://unsplash.com/documentation#list-photos) to show list of photos

This application used Clean Architecture approach for structuring the code.

```
package
└───core
|   └───  di
|   └───  extension    
|   └───  presentation
|
└───data
│   └───  model
│   └───  source
|
└───presentation
│   └───  adapter
│   └───  listphotos
|   |     Navigator.kt
└───utils
```

## Unsplash API
For running this application, you have to register and get Unsplash Access Key by visitting this link
` https://unsplash.com/oauth/applications`

## Running the app
After you get the key, you need to save the API Key, here's the steps:
1. Create file `lib.cpp` under the directory `./src/main/cpp`
2. Paste this code inside the `lib.cpp` file
```
#include <jni.h>
#include <string>
extern "C"
JNIEXPORT jstring JNICALL
Java_com_scrapps_galleryapplication_utils_Keys_accessKey(JNIEnv *env, jobject thiz) {
    std::string client_id = "<YOUR_KEY_HERE>";
    return env->NewStringUTF(client_id.c_str());
}
```