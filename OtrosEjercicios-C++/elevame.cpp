#include <iostream>
using namespace std;

const int MAX = 31543;

long long int elevame(long long int x, long long int n)
{
	long long int dyv, dyv2;
	if (n == 0)
	{
		return 1;
	}
	else if (n == 1)
	{
		return x % MAX;
	}
	else
	{
		if ((n % 2) == 0)//par
		{
			dyv = elevame(x, (n / 2));
			dyv *= dyv;
		}
		else//impar
		{
			dyv = elevame(x, (n / 2));
			dyv2 = dyv * x;
			dyv *= dyv2;
		}
	}
	dyv %= MAX;
	return dyv;
}

bool resuelveCaso()
{
	long long int x, n, q;

	cin >> x >> n;

	if (x == 0 && n == 0) return false;
	else {
		q = elevame(x, n);
		cout << q << "\n";
		return true;
	}

	return false;
}

int main()
{
	while (resuelveCaso());

	//system("pause");
	return 0;
}