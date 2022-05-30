///*
// * Copyright 2021-2022 John A. De Goes and the ZIO Contributors
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *     http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */

package zio.flow.remote

import zio.flow._
import zio.flow.remote.numeric._

class RemoteNumericSyntax[A](self: Remote[A]) {

  final def +(that: Remote[A])(implicit numeric: Numeric[A]): Remote[A] =
    Remote.BinaryNumeric(self.widen[A], that, numeric, BinaryNumericOperator.Add)

  final def /(that: Remote[A])(implicit numeric: Numeric[A]): Remote[A] =
    Remote.BinaryNumeric(self.widen[A], that, numeric, BinaryNumericOperator.Div)

  final def %(that: Remote[A])(implicit numeric: Numeric[A]): Remote[A] =
    mod(that)

  final def *(that: Remote[A])(implicit numeric: Numeric[A]): Remote[A] =
    Remote.BinaryNumeric(self.widen[A], that, numeric, BinaryNumericOperator.Mul)

  final def unary_-(implicit numeric: Numeric[A]): Remote[A] =
    Remote.UnaryNumeric(self.widen[A], numeric, UnaryNumericOperator.Neg)

  final def -(that: Remote[A])(implicit numeric: Numeric[A]): Remote[A] =
    Remote.BinaryNumeric(self.widen[A], -that, numeric, BinaryNumericOperator.Add)

  final def pow(exp: Remote[A])(implicit numeric: Numeric[A]): Remote[A] =
    Remote.BinaryNumeric(self.widen[A], exp, numeric, BinaryNumericOperator.Pow)

  final def root(n: Remote[A])(implicit numeric: Numeric[A]): Remote[A] =
    Remote.BinaryNumeric(self.widen[A], n, numeric, BinaryNumericOperator.Root)

  final def log(base: Remote[A])(implicit numeric: Numeric[A]): Remote[A] =
    Remote.BinaryNumeric(self.widen[A], base, numeric, BinaryNumericOperator.Log)

  final def mod(that: Remote[A])(implicit numeric: Numeric[A]): Remote[A] =
    Remote.BinaryNumeric(self, that, numeric, BinaryNumericOperator.Mod)

  final def abs(implicit numeric: Numeric[A]): Remote[A] =
    Remote.UnaryNumeric(self.widen[A], numeric, UnaryNumericOperator.Abs)

  final def min(that: Remote[A])(implicit numeric: Numeric[A]): Remote[A] =
    Remote.BinaryNumeric(self.widen[A], that, numeric, BinaryNumericOperator.Min)

  final def max(that: Remote[A])(implicit numeric: Numeric[A]): Remote[A] =
    Remote.BinaryNumeric(self.widen[A], that, numeric, BinaryNumericOperator.Max)

  final def floor(implicit numeric: Numeric[A]): Remote[A] =
    Remote.UnaryNumeric(self.widen[A], numeric, UnaryNumericOperator.Floor)

  final def ceil(implicit numeric: Numeric[A]): Remote[A] =
    Remote.UnaryNumeric(self.widen[A], numeric, UnaryNumericOperator.Ceil)

  final def round(implicit numeric: Numeric[A]): Remote[A] =
    Remote.UnaryNumeric(self.widen[A], numeric, UnaryNumericOperator.Round)
}
