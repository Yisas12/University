#include <iostream>
#include <vector>
using namespace std;

void resuelve(int casos)
{
	int max = 0, cont = 0;
	long long int sum = 0, nums = 0;
	bool b = false;
	vector<long long int> v;

	for (int i = 0; i < casos; i++)
	{
		cin >> nums;
		v.push_back(nums);
	}

	for (int i = v.size() - 1; i >= 0; i--)
	{
		if (v[i] == sum)
		{
			if (v[i] > max)
			{
				max = v[i];
				cont = i + 1;
				b = true;
			}
		}
		sum += v[i];
	}

	if(b) cout << "SI " << cont << endl;
	else cout << "NO" << endl;

	for (int i = 0; i < casos; i++)
	{
		v.pop_back();
	}
}

int main()
{
	int casos;
	
	cin >> casos;

	while (casos > 0) {
		resuelve(casos);
		cin >> casos;
	}

	//system("pause");
	return 0;
}