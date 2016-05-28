package com.example.jdk8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

import com.google.common.collect.Lists;

//参考http://ifeve.com/stream/
public class StreamTest {
	@Test
	public void test() {
		// 1
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		Stream<Integer> stream = numbers.stream();
		stream.filter((x) -> {
			return x % 2 == 0;
		}).map((x) -> {
			return x * x;
		}).forEach(System.out::println);

		// 2

		List<Integer> nums = Lists.newArrayList(1, null, 3, 4, null, 6);
		nums.stream().filter(num -> num != null).count();

	}

	@Test
	public void testCreateStream() {
		Stream<Integer> integerStream = Stream.of(1, 2, 3, 5);
		Stream<String> stringStream = Stream.of("taobao");
		// 无限长的supplier都会配合limit使用
		Stream.generate(new Supplier<Double>() {
			@Override
			public Double get() {
				return Math.random();
			}
		});
		Stream.generate(() -> Math.random());
		Stream.generate(Math::random);

		Stream.iterate(1, item -> item + 1).limit(10).forEach(System.out::println);

	}

	@Test
	public void testDistince() {

		List<Integer> nums = Lists.newArrayList(1, 1, null, 2, 3, 4, null, 5, 6, 7, 8, 9, 10);
		System.out.println("sum is:" + nums.stream().filter(num -> num != null).distinct().mapToInt(num -> num * 2)
				.peek(System.out::println).skip(2).limit(4).sum());

	}

	@Test
	public void testCollectors() {
		List<Integer> nums = Lists.newArrayList(1, 1, null, 2, 3, 4, null, 5, 6, 7, 8, 9, 10);
		List<Integer> numsWithoutNull = nums.stream().filter(num -> num != null).collect(Collectors.toList());

	}

	@Test
	public void testReduce() {

		List<Integer> ints = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		System.out.println("ints sum is:" + ints.stream().reduce((sum, item) -> sum + item).get());
		System.out.println("ints sum is:" + ints.stream().reduce(0, (sum, item) -> sum + item));
	}

	@Test
	public void testSearch() {

		List<Integer> ints = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		System.out.println(ints.stream().allMatch(item -> item < 100));
		ints.stream().max((o1, o2) -> o1.compareTo(o2)).ifPresent(System.out::println);

	}

	@Test
	public void testSupplier() {
		Stream<Long> stream = Stream.generate(new NaturalSupplier());
		stream.map((x) -> {
			return x * x;
		}).skip(20).limit(10).forEach(System.out::println);
		;
	}

	static class NaturalSupplier implements Supplier<Long> {

		long value = 0;

		public Long get() {
			this.value = this.value + 1;
			return this.value;
		}
	}

}
