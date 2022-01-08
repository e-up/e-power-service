import 'dart:async';

import 'package:flutter/services.dart';

class EPowerService {
  static const MethodChannel _channel = const MethodChannel('e_power_service');

  static Future<void> request() async {
    _channel.invokeMethod("request");
  }
}
