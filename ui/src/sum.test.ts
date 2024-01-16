test('add numbers', () => {
  const f = (a: number, b: number) => a + b;

  expect(f(3, 4)).toBe(7);
  expect(f(5, 10)).not.toBe(7);
});
