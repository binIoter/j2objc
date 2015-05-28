/*
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
package com.google.devtools.j2objc.ast;

import com.google.common.collect.Lists;

import org.eclipse.jdt.core.dom.ITypeBinding;

import java.util.List;

/**
 * Abstract base class of all AST node types that represent a method reference expression (added in
 * JLS8).
 *
 * <pre>
 * MethodReference:
 *    CreationReference
 *    ExpressionMethodReference
 *    SuperMethodReference
 *    TypeMethodReference
 * </pre>
 */
public abstract class MethodReference extends Expression {
  private final ITypeBinding typeBinding;
  private List<Type> typeArguments;

  public MethodReference(org.eclipse.jdt.core.dom.MethodReference jdtNode) {
    super(jdtNode);
    typeBinding = jdtNode.resolveTypeBinding();
    typeArguments = Lists.newArrayListWithExpectedSize(jdtNode.typeArguments().size());
    for (Object x : jdtNode.typeArguments()) {
      typeArguments.add((Type) TreeConverter.convert(x));
    }
  }

  public MethodReference(MethodReference other) {
    super(other);
    typeBinding = other.getTypeBinding();
    typeArguments = other.typeArguments();
  }

  @Override
  public ITypeBinding getTypeBinding() {
    return typeBinding;
  }

  public List<Type> typeArguments() {
    return typeArguments;
  }

}