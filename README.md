# MdToLinkedInPost Converter (quick solution)

## Problem statement

I often create repositories with examples and use the `README.md` file as a draft for articles that I later publish on `LinkedIn` and `Medium`. The problem is that I want to maintain a consistent style in my `LinkedIn` posts, but `LinkedIn` does not provide a proper editor. Therefore, I need a tool to convert regular `ANSI`-table characters into extended `UTF-32` symbols to make headers and bold characters look bold **bold** and code and italic characters _italic_.
Also, I want to format links and bullet lists in human-readable form.

As a user, I need a solution to take a `.md` file and prepare a LinkedIn-compatible version.

## Solution

### Variant 1

Since the JVM uses UTF-16, accessing bold and italic characters requires the use of an extended Unicode table, which does not fit into a 16-bit `char`. This can be solved using [surrogate pairs](https://learn.microsoft.com/en-us/windows/win32/intl/surrogates-and-supplementary-characters). A surrogate pair divides one character into two: a high surrogate and a low surrogate.

### Variant 2

Alternatively, we can store ready-to-use characters in an array and calculate the position of a character as `'A'..'Z' -> adjacencyList[char - 'A']`. Where we map regular letters to Unicode characters from the extended table. This approach is less memory efficient but quicker to implement. For now, I prioritize speed over the complexity of calculating code points and verifying surrogate pairs.

## Future Work

This is a quick solution that solves my immediate need for automating the conversion of `.md` files into LinkedIn-styled text. In the future, I may refactor the code or add more features if I have time, but for now, I do not plan to make any changes. 😊
