#include <iostream>
#include <time.h>
#include <stdio.h>
using namespace std;

int main() {
	time_t sec;
	sec = time(NULL);
	cout << sec << '\n';
	return 1;
}