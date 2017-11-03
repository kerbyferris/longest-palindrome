import pytest
from palindrome import get_pivot, is_palindrome, longest_palindrome 

some_palindromes = ('a',
                    'ana',
                    'anna',
                    'abba',
                    'asantaatnasa',
                    'atoyotasatoyota',
                    'xacaramanamaracax')
some_not_palindromes = ('',
                        'xabba',
                        'abbax',
                        'whatever')

def test_get_pivot():
    assert get_pivot('abba', 0) == (0, 0)
    assert get_pivot('abba', 1) == (1, 2)
    assert get_pivot('abba', 2) == (2, 2)
    assert get_pivot('abba', 3) == (3, 3)

def test_is_palindrome():
    assert all([is_palindrome(p) for p in some_palindromes])
    assert not any([is_palindrome(np) for np in some_not_palindromes])

def test_finds_palindrome():
    str = 'abba'
    assert longest_palindrome(str) == 'abba'

def test_finds_longest_palindrome():
    str = 'abbaracecar'
    assert longest_palindrome(str) == 'racecar'

def test_ignores_unpalindromatic_chars():
    str = 'blahracecarwhatever'
    assert longest_palindrome(str) == 'racecar'

def test_finds_palindrome_with_pivot():
    str = 'abbaxabba'
    assert longest_palindrome(str) == 'abbaxabba'

def test_handles_sort_of_long_string():
    str = ''.join(some_palindromes + some_not_palindromes) * 10
    assert longest_palindrome(str) == 'xacaramanamaracax'

@pytest.mark.skip('nope to self')
def test_handles_crazy_long_string_with_crazy_long_palindrome():
    str = ''.join(
        [str * 100 for str in (some_palindromes + some_not_palindromes)]
    )
    assert longest_palindrome(str) == 'racecaratoyotasatoyotaracecar'

