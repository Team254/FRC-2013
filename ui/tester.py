import SocketServer
import math
import time

class TCPHandler(SocketServer.BaseRequestHandler):
    """
    The RequestHandler class for the TCP server.

    It is instantiated once per connection to the server, and must
    override the handle() method to implement communication to the
    client.
    """

    def handle(self):
        # self.request is the TCP socket connected to the client
        i = 0
        while True:
            msg= '{"S":%d, "V":%d, "C":%d}' % (((math.sin(i) + 1) * 50), ((math.cos(i) + 1) * 50), (math.cos(i)))
            self.data = msg
            self.request.sendall(self.data)
            print self.data
            time.sleep(.1)
            i += .1

        #self.data = self.request.recv(1024).strip()
        #print "{} wrote:".format(self.client_address[0])


if __name__ == "__main__":
    HOST, PORT = "localhost", 41234

    # Create the TCP server
    server = SocketServer.TCPServer((HOST, PORT), TCPHandler)

    # Activate the server; kill it with ctrl+c
    print "Running TCP server on %s:%i" % (HOST, PORT)
    server.serve_forever()
