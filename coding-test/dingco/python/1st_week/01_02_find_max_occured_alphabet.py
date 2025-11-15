def find_max_occurred_alphabet(string):
    alphabet_occurred_list = [0] * 26
    for ch in string:
        if ch.isalpha():
            idx = ord(ch) - ord('a')
            alphabet_occurred_list[idx] += 1

    max_idx = 0
    for idx in range(1, 26):
        if alphabet_occurred_list[max_idx] < alphabet_occurred_list[idx]:
            max_idx = idx

    return chr(max_idx + ord('a'))


result = find_max_occurred_alphabet
print("정답 = i 현재 풀이 값 =", result("hello my name is dingcodingco"))
print("정답 = e 현재 풀이 값 =", result("we love algorithm"))
print("정답 = b 현재 풀이 값 =", result("best of best youtube"))