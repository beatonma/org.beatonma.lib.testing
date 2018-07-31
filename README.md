# Testing
## org.beatonma.lib.testing

**This module is not intended for 3rd party use**, although you are free to use any parts of it
you like (with attribution). Be aware that APIs are subject to change without notice and
support will be limited.

This module is part of a larger group of libraries that I use via a private Artifactory repository.
It is not currently available on any public repositories.

The parent project holds most of the build configuration but is not available publicly. If you
really want to build this module you will need to write your own `build.gradle` configuration.
The dependencies you need to include can be found listed in `dependencies.txt`.

----

This module provides some handy tools and convenience functions for making readable unit tests and
UI testing.

### Examples

#### Assertions
 - oneThing.assertEquals(anotherThing)
 - something.assertTrue()

#### Mockito
- val mockObject = mock<ClassToMock>()

#### Espresso
- Clicks and swipes with customisable start/end position, length, duration.
- Matchers for checking view layout parameters.

#### Rules
Run an activity with custom Intent extras with:
    ActivityClass::class.testRule { it.putExtra(EXTRA_SOMETHING, false) }

...

etc
