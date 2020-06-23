//Alunos Gustavo Jacob e Joseph Weber


import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


class Job {
	int start, finish, profit;

	Job(int start, int finish, int profit) {
		this.start = start;
		this.finish = finish;
		this.profit = profit;
	}
}

public class ex1
{
	// Function to find index of last job which doesn't conflict with the given job
	// It performs a linear search on the given List of jobs
	public static int findLastNonConflictingJob(List<Job> jobs, int n)
	{
		// find index of the last job whose finish time is less than or equal to the
		// start time of the given job
		for (int i = n - 1; i >= 0; i--) {
			if (jobs.get(i).finish <= jobs.get(n).start) {
				return i;
			}
		}

		// return negative index if no non-conflicting job is found
		return -1;
	}

	// A recursive function to find maximum profit subset of non-overlapping
	// jobs which are sorted according to finish time
	public static int maxProfit(List<Job> jobs, int n)
	{
		// sort jobs in increasing order of their finish times
		Collections.sort(jobs, Comparator.comparingInt(x -> x.finish));

		// base case
		if (n < 0) {
			return 0;
		}

		// return if only one item is remaining
		if (n == 0) {
			return jobs.get(0).profit;
		}

		// find the index of last non-conflicting job with current job
		int index = findLastNonConflictingJob(jobs, n);

		// include the current job and recur for non-conflicting jobs [0, index]
		int incl = jobs.get(n).profit + maxProfit(jobs, index);

		// exclude the current job and recur for remaining items [0, n-1]
		int excl = maxProfit(jobs, n-1);

		// return the maximum profit by including or excluding current job
		return Math.max(incl, excl);
	}

	public static void main(String[] args)
	{

		List<Job> jobs = Arrays.asList(
				new Job(0, 6, 60),
				new Job(1, 4, 30),
				new Job(3, 5, 10),
				new Job(5, 7, 30),
				new Job(5, 9, 50),
				new Job(7, 8, 10)
		);

		System.out.print("The maximum profit is: " + maxProfit(jobs, jobs.size() - 1) + "\n " );
	}
}