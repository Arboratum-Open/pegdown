/*
 * Copyright (C) 2010-2011 Mathias Doenitz
 *
 * Based on peg-markdown (C) 2008-2010 John MacFarlane
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.pegdown;

import org.testng.Assert;

public class TestUtils {

    public static void assertEqualsMultiline(String actual, String expected) {
        Assert.assertEquals(
                actual.replaceAll("\\s+", " ").replaceAll("\\s*(\r\n|\n|\r)", "").replaceAll(">\\s+<", "><"),
                expected.replaceAll("\\s+", " ").replaceAll("\\s*(\r\n|\n|\r)", "").replaceAll(">\\s+<", "><")
        );
    }

}
