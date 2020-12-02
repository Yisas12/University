#include <time.h>
#include <iostream>
#include <time.h>
#include <stdio.h>
using namespace std;

int main() {
	time_t t;
	struct tm *tm;
	t = time(NULL);
	tm = localtime(&t);
	cout << 1900 + tm->tm_year << '\n';

	return 1;
}