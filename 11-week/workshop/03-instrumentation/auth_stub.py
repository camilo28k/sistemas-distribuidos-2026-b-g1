"""Local synthetic Auth transport for the registration instrumentation exercise."""
import argparse
import json
import time
from http.server import BaseHTTPRequestHandler, ThreadingHTTPServer

parser = argparse.ArgumentParser()
parser.add_argument('--port', type=int, default=8091)
parser.add_argument('--status', type=int, choices=[201, 409, 503], default=201)
parser.add_argument('--delay', type=float, default=0)
options = parser.parse_args()

class AuthStub(BaseHTTPRequestHandler):
    def do_POST(self):
        self.rfile.read(int(self.headers.get('Content-Length', 0)))
        if self.path != '/api/v1/auth/register':
            self.send_error(404)
            return
        print(json.dumps({'stub': 'auth-service', 'traceparent': self.headers.get('traceparent')}), flush=True)
        time.sleep(options.delay)
        body = json.dumps({'status': 'PENDING_VERIFICATION'} if options.status == 201 else {'error': 'SYNTHETIC_FAILURE'}).encode()
        self.send_response(options.status)
        self.send_header('Content-Type', 'application/json')
        self.send_header('Content-Length', str(len(body)))
        self.end_headers()
        try:
            self.wfile.write(body)
        except (BrokenPipeError, ConnectionResetError, ConnectionAbortedError):
            pass

    def log_message(self, *_):
        pass

ThreadingHTTPServer(('127.0.0.1', options.port), AuthStub).serve_forever()
