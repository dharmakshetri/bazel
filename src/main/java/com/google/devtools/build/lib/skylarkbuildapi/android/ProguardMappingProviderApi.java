// Copyright 2018 The Bazel Authors. All rights reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package com.google.devtools.build.lib.skylarkbuildapi.android;

import com.google.devtools.build.lib.skylarkbuildapi.FileApi;
import com.google.devtools.build.lib.skylarkbuildapi.ProviderApi;
import com.google.devtools.build.lib.skylarkbuildapi.StructApi;
import com.google.devtools.build.lib.skylarkinterface.Param;
import com.google.devtools.build.lib.skylarkinterface.SkylarkCallable;
import com.google.devtools.build.lib.skylarkinterface.SkylarkConstructor;
import com.google.devtools.build.lib.skylarkinterface.SkylarkModule;
import com.google.devtools.build.lib.skylarkinterface.SkylarkModuleCategory;
import com.google.devtools.build.lib.syntax.EvalException;

/** A target that can provide a proguard obfuscation mapping to Android binaries or tests. */
@SkylarkModule(
    name = "ProguardMappingProvider",
    doc = "Information about the Proguard mapping provided by a rule.",
    category = SkylarkModuleCategory.PROVIDER)
public interface ProguardMappingProviderApi<FileT extends FileApi> extends StructApi {

  public static final String NAME = "ProguardMappingInfo";

  @SkylarkCallable(name = "proguard_mapping", structField = true, doc = "", documented = false)
  FileT getProguardMapping();

  /** The provider implementing this can construct the ProguardMappingProvider provider. */
  @SkylarkModule(name = "Provider", doc = "", documented = false)
  public interface Provider<FileT extends FileApi> extends ProviderApi {

    @SkylarkCallable(
        name = NAME,
        doc = "The <code>ProguardMappingInfo</code> constructor.",
        parameters = {
          @Param(
              name = "proguard_mapping",
              doc = "An artifact of the proguard mapping.",
              positional = true,
              named = false,
              type = FileApi.class),
        },
        selfCall = true)
    @SkylarkConstructor(objectType = ProguardMappingProviderApi.class, receiverNameForDoc = NAME)
    public ProguardMappingProviderApi<FileT> createInfo(FileT proguardMapping) throws EvalException;
  }
}
