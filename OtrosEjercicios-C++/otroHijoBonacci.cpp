#include <iostream>
#include <math.h>
using namespace std;

const long long int MAX = 1000000007;

typedef struct {
	long long int r;
	long long int ant;
}bona;

bona bonacci(int n, long long int x, long long int y)
{
	long long int aux = 0; //m = 0;
	bona b;
	if (n == 0) {
		b.r = x;
		b.ant = 0;
	}
	else if (n == 1) {
		b.r = y;
		b.ant = x;
	}
	else
	{
		//r = bonacci(n - 1, x, y) + bonacci(n - 2, x, y);
		b = bonacci(n - 1, x, y);
		aux = b.r + b.ant;
		b.ant = b.r % MAX;;
		b.r = aux % MAX;
	}

	return b;
}

int main()
{
	long long int x, y;
	int n;
	bona c;
	//long long int m = pow(10, 9) + 7;

	cin >> n;
	while (cin)
	{
		cin >> x;
		cin >> y;

		c = bonacci(n, x, y);

		cout << c.r << '\n';

		cin >> n;
	}

	//system("pause");
	return 0;
}