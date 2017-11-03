def is_palindrome(str):
    half = len(str) // 2
    return str[:half] == str[-half:][::-1]

def get_pivot(str, start_idx):
    single_pivot = (start_idx, start_idx)
    double_pivot = (start_idx, (start_idx + 1))

    try:
        if(str[start_idx] == str[start_idx + 1]):
            return double_pivot
        else:
            return single_pivot
    except IndexError:
        return single_pivot

def get_palindrome(str, start_idx, end_idx):
    palindrome = str[start_idx:(end_idx + 1)]

    maybe_start_idx = start_idx - 1 if start_idx > 0 else 0
    maybe_end_idx = end_idx + 1
    maybe_palindrome = str[maybe_start_idx:(maybe_end_idx + 1)]

    if(not is_palindrome(maybe_palindrome)):
        return palindrome
    elif(maybe_start_idx == 0 or maybe_end_idx == (len(str) - 1)):
        return maybe_palindrome
    else:
        return get_palindrome(str, maybe_start_idx, maybe_end_idx)

def longest_palindrome(str, longest='', idx=0):
    if(idx == len(str) - 1):
        return longest

    palindrome = get_palindrome(str, (get_pivot(str, idx)))

    if(len(palindrome) > len(longest)):
        return longest_palindrome(str, palindrome, idx + 1)
    else:
        return longest_palindrome(str, longest, idx + 1)


if __name__ == "__main__":
    str = input('Enter String: ')

    print(longest_palindrome(str))
