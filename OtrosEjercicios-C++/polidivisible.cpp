#include <iostream>
using namespace std;
void polidivisible(long long int &numero, long long int &l)
{
	if (l > 0 && numero % l == 0)
	{
		numero /= 10;
		l -= 1;
		polidivisible(numero, l);
	}
	else if (l == 0)
	{
		cout << "POLIDIVISIBLE" << endl;
	}
	else
	{
		cout << "NO POLIDIVISIBLE" << endl;
	}
}

int main()
{
	long long int nums, l = 0, aux = 0;

	while (cin >> nums && nums > 0)
	{
		aux = nums;
		while (nums > 0)
		{
			nums /= 10;
			l++;
		}
		polidivisible(aux, l);
		l = 0;
		aux = 0;
	}

	//system("pause");
	return 0;
}