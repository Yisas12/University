#include <time.h>
#include <iostream>
#include <time.h>
#include <stdio.h>
using namespace std;

int main() {
	time_t t;
	struct tm *tm;
	t = time(NULL);
	char fecha[128];

	tm = localtime(&t);
	strftime(fecha, 128, "%A %B %Y %H:%M", tm);
	cout << fecha << '\n';

	return 1;
}