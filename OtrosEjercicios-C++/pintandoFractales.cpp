#include <iostream>
#include <vector>
using namespace std;

long long int fractales(int &num, int &cuadrados, long long int &suma)
{
	long long int p;

	if (num == 0) return 0;
	else if (num == 1)
	{
		suma += num * 4 * cuadrados;
	}
	else
	{
		suma += num * 4 * cuadrados;
		cuadrados *= 4;
		num /= 2;
		//if (num / 2 != 1)
		//{
			p = fractales(num, cuadrados, suma);
		//}
	}

	return suma;
}

int main()
{
	int nums, cuad = 1;
	long long int p, suma = 0;

	while (cin >> nums)
	{
		p = fractales(nums, cuad, suma);
		cout << p << endl;
		suma = 0;
		cuad = 1;
	}

	//system("pause");
	return 0;
}