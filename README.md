# nice-validator

java版本的nice-validator，参考了前端的校验框架[nice-validator](https://github.com/niceue/nice-validator)

## required

|规则|描述
|-|-
|required |字段必填 

## length

|规则|描述
|-|-
|length(n)	    |请填写 n 个字符
|length(n~)	    |请至少填写 n 个字符
|length(~n)	    |请最多填写 n 个字符
|length(n1~n2)	|请填写 n1 到 n2 个字符

## checked

|规则|描述
|-|-
|checked	    |必选
|checked(n)	    |必选 n 项
|checked(n~)	|至少选择 n 项
|checked(~n)	|最多选择 n 项
|checked(n1~n2)	|选择 n1 到 n2 项

## match

|规则|描述
|-|-
|match(name)	        |当前字段值必须和 name 字段的值匹配
|match(eq, name)	    |同上
|match(neq, name)	    |当前字段值必须和 name 字段值不同
|match(lt, name)	    |当前字段值必须小于 name 字段值
|match(gt, name)	    |当前字段值必须大于 name 字段值
|match(lte, name)	    |当前字段值必须小于等于 name 字段值
|match(gte, name)	    |当前字段值必须大于等于 name 字段值

## integer

|规则|描述
|-|-
|integer	    |整数
|integer(+)	    |正整数
|integer(+0)	|正整数和零
|integer(-)	    |负整数
|integer(-0)	|负整数和零

## range

|规则|描述
|-|-
|range(n~)		|请填写不小于 n 的数
|range(~n)		|请填写不大于 n 的数
|range(n1~n2)	|请填写 n1 到 n2 的数
|range(n1~n2, false)	|请填写 n1 到 n2 的数（不包含边界值）

## regex

|规则|描述
|-|-
|regex(expression)		|请填写符合格式的内容

## custom

|规则|描述
|-|-
|custom(className) |自定义类，实现Validator接口，编写校验逻辑,“className为全路径类名”