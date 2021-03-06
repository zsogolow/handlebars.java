/**
 * Copyright (c) 2012-2015 Edgar Espina
 *
 * This file is part of Handlebars.java.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.jknack.handlebars.io;

import java.io.IOException;

import static org.apache.commons.lang3.StringUtils.defaultString;
import static org.apache.commons.lang3.Validate.notNull;

/**
 * <p>
 * Strategy interface for loading resources from class path, file system, etc.
 * </p>
 * <h3>Templates prefix and suffix</h3>
 * <p>
 * A <code>TemplateLoader</code> provides two important properties:
 * </p>
 * <ul>
 * <li>prefix: useful for setting a default prefix where templates are stored.</li>
 * <li>suffix: useful for setting a default suffix or file extension for your templates. Default is:
 * <code>'.hbs'</code></li>
 * </ul>

 * @author edgar.espina
 * @since 1.0.0
 */
public abstract class AbstractTemplateLoader implements TemplateLoader {

  /**
   * The prefix that gets prepended to view names when building a URI.
   */
  private String prefix = DEFAULT_PREFIX;

  /**
   * The prefix that gets prepended to partial views names when building a URI.
   */
  private String partialsPrefix = prefix;

  /**
   * The suffix that gets appended to view names when building a URI.
   */
  private String suffix = DEFAULT_SUFFIX;

  /**
   * Resolve the uri to an absolute location.
   *
   * @param uri The candidate uri.
   * @return Resolve the uri to an absolute location.
   */
  @Override
  public String resolve(final String uri) {
    return prefix + normalize(uri) + suffix;
  }

  /**
   * Resolve the uri to an absolute location for partials.
   *
   * @param uri The candidate uri.
   * @return Resolve the uri to an absolute location.
   */
  public String resolvePartial(final String uri) {
    return partialsPrefix + normalize(uri) + suffix;
  }

  /**
   * Normalize the location by removing '/' at the beginning.
   *
   * @param location The candidate location.
   * @return A location without '/' at the beginning.
   */
  protected String normalize(final String location) {
    if (location.toString().startsWith("/")) {
      return location.substring(1);
    }
    return location;
  }

  /**
   * Set the prefix that gets prepended to view names when building a URI.
   *
   * @param prefix The prefix that gets prepended to view names when building a
   *        URI.
   */
  public void setPrefix(final String prefix) {
    this.prefix = notNull(prefix, "A view prefix is required.");
    if (!this.prefix.endsWith("/")) {
      this.prefix += "/";
    }
  }

  /**
   * Set the prefix that gets prepended to view names when building a URI
   * for partials.
   *
   * @param prefix The prefix that gets prepended to view names when building a
   *        URI for partials.
   */
  @Override
  public void setPartialsPrefix(String prefix) {
    this.partialsPrefix = notNull(prefix, "A partials prefix is required.");
    if (!this.partialsPrefix.endsWith("/")) {
      this.partialsPrefix += "/";
    }
  }

  /**
   * Set the suffix that gets appended to view names when building a URI.
   *
   * @param suffix The suffix that gets appended to view names when building a
   *        URI.
   */
  public void setSuffix(final String suffix) {
    this.suffix = defaultString(suffix, "");
  }

  /**
   * @return The prefix that gets prepended to view names when building a URI.
   */
  @Override
  public String getPrefix() {
    return prefix;
  }

  /**
   * @return The prefix that gets prepended to view names when building a URI
   * for a partial.
   */
  @Override
  public String getPartialsPrefix() {
    return partialsPrefix;
  }

  /**
   * @return The suffix that gets appended to view names when building a
   *         URI.
   */
  @Override
  public String getSuffix() {
    return suffix;
  }
}
