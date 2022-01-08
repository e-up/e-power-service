import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:e_power_service/e_power_service.dart';

void main() {
  const MethodChannel channel = MethodChannel('e_power_service');

  TestWidgetsFlutterBinding.ensureInitialized();

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });
}
