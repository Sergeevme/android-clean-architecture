# Simple example of Android Clean Architecture

**Simple example of Android Clean Architecture: save and load values**.

Considering the common architectural principles mentioned in the previous section, each application should have at least two layers:
- The UI layer that displays application data on the screen.
- The data layer that contains the business logic of your app and exposes application data.

You can add an additional layer called the domain layer to simplify and reuse the interactions between the UI and data layers.

![android app architecture](/images/android.png "Android App architecture")

## Technologies

**LiveData** — to observe and react to changes in data, particularly data that is part of the UI. \
**Koin** — pragmatic and lightweight dependency injection framework for Kotlin developers. \
**Modularization** — for code sharing and building. In this project it is used to split the application into three modules: presentation, data, domain. \
**MVVM** — architectural pattern in computer software that facilitates the separation of the development of the UI. \
**Flow** — great fit for live data updates and endless streams of data. \
**Coroutines** — converts async callbacks for long-running tasks into sequential code. \
**Material3** — design system built and supported by Google designers and developers.

## Project UI

![app UI](/images/app-ui-1.png "App UI") ![app UI](/images/app-ui-2.png "App UI")

## Code examples

```kotlin
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(listOf(appModule, dataModule, domainModule))
        }
    }

}
```

```kotlin
private fun <T> ComponentActivity.collectLatestLifecycleFlow(
        flow: Flow<T>, 
        collect: suspend (T) -> Unit
    ) {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                flow.collectLatest(collect)
            }
        }
    }
```

## License

[MIT: ](https://choosealicense.com/licenses/mit/) Copyright (c) 2023 sergeevme

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
